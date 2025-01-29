package com.io.scm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.io.scm.repository.UserRepo;

@Service
public class SecurityCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // in our case we take email as our username
    // if username is not found ,it is handled by login form functionalties in
    // SecurityConfig
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));

    }

}
