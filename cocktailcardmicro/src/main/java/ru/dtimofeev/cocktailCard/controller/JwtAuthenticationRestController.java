package ru.dtimofeev.cocktailCard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.cocktailCard.controller.dto.JwtTokenRequest;
import ru.dtimofeev.cocktailCard.controller.dto.JwtTokenResponse;
import ru.dtimofeev.cocktailCard.exception.AuthenticationException;
import ru.dtimofeev.cocktailCard.logging.Loggable;
import ru.dtimofeev.cocktailCard.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ru.dtimofeev.cocktailCard.security.SecurityConstants.HTTP_TOKEN_AUTH_HEADER;

@Loggable
@RestController
public class JwtAuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/auth/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(HTTP_TOKEN_AUTH_HEADER);
        String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (!jwtTokenUtil.isTokenExpired(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token, username);
            return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}

