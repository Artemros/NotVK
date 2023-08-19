package com.notvk.server.controller;

import com.notvk.server.model.UserInfo;
import com.notvk.server.service.RegistrationService;
import com.notvk.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

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
    public String goToHomePage(Model model) throws IOException {
        model.addAttribute("name",new String());
        model.addAttribute("password",new String());
        model.addAttribute("form",new UserInfo());
        return "RegistrationForm.html";
    }

    @PostMapping("/a")
    public String acceptFormInput(Model model, @ModelAttribute UserInfo userInfo) {
        UserInfo registeredUser = new UserInfo();
        //String gotText = Objects.requireNonNull(model.getAttribute("name")).toString();
        registeredUser.setName(userInfo.getName());
        registeredUser.setPassword(userInfo.getPassword());
        UserInfo newUser = userService.registerNewUser(registeredUser);
        logger.info("registred user"+ newUser);
        return "redirect:/id" + newUser.getId();
    }
}
