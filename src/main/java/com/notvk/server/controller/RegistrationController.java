package com.notvk.server.controller;

import com.notvk.server.model.UserInfo;
import com.notvk.server.service.RegistrationService;
import com.notvk.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService registrationService;
    private final UserService userService;

    @GetMapping("")
    public String goToRegistrationPage(Model model) {
        model.addAttribute("name", "");
        model.addAttribute("password", "");
        model.addAttribute("form", new UserInfo());
        return "RegistrationForm.html";
    }

    @PostMapping("/a")
    public String acceptRegistrationFormInput(Model model, @ModelAttribute UserInfo userInfo) {
        UserInfo registeredUser = new UserInfo();
        registeredUser.setName(userInfo.getName());
        registeredUser.setPassword(userInfo.getPassword());
        UserInfo newUser = userService.registerNewUser(registeredUser);
        registrationService.register(userInfo.getName(),userInfo.getPassword());
        logger.info("registered user" + newUser);
        return "redirect:/id" + newUser.getId();
    }
}
