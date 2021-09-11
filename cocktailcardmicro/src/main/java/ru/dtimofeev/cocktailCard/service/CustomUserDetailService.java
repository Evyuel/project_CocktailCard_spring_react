package ru.dtimofeev.cocktailCard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.cocktailCard.repository.AppUserDetailsRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AppUserDetailsRepository appUserDetailsRepository;

    @Autowired
    public CustomUserDetailService(AppUserDetailsRepository appUserDetailsRepository) {
        this.appUserDetailsRepository = appUserDetailsRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return appUserDetailsRepository.findById(login).orElseThrow(() -> new UsernameNotFoundException("User with login: " + login + "not found."));
    }
}
