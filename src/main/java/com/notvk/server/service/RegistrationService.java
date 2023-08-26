package com.notvk.server.service;

import com.notvk.server.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserService userService;


    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public void registerNewUser(UserInfo userInfo) {

//        securityDetailManager.createUser();
//        userService.registerNewUser();
    }
}
