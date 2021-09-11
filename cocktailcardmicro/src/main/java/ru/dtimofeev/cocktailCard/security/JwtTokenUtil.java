package ru.dtimofeev.cocktailCard.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ru.dtimofeev.cocktailCard.security.SecurityConstants.*;

@Component
public class JwtTokenUtil implements Serializable {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_EXP_IN = "exp";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, CLAIM_KEY_USERNAME);
    }

    public Date getExpirationDateFromToken(String token) {
        Date d = getDateFromTokenString(getClaimFromToken(token, CLAIM_KEY_EXP_IN));
        return d;
    }

    private Date getDateFromTokenString(String milliSec) {
        long milliSeconds = Long.parseLong(milliSec);
        Date d = new Date(new Date().getTime() + milliSeconds);
        return d;
    }

    public String getClaimFromToken(String token, String claimName) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] payload = decoder.decode(token.split("\\.")[1]);
        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readTree(payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode.get(claimName).asText();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return TOKEN_PREFIX + JWT.create()
                .withSubject(subject)
                .withArrayClaim(ROLES_CLAIM, new String[]{})
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String refreshToken(String token, String username) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return JWT.create()
                .withSubject(username)
                .withArrayClaim(ROLES_CLAIM, new String[]{})
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        return true;
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + EXPIRATION_TIME);
    }
}

