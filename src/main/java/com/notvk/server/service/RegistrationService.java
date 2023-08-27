package com.notvk.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    private final UserDetailsManager userDetailsManager;

    @Autowired
    public RegistrationService(UserService userService, PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    public void register(String userName, String password) {
        UserDetails newUser = User.withUsername(userName)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(newUser);
    }
}
