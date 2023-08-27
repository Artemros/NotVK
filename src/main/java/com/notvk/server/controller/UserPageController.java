package com.notvk.server.controller;

import com.notvk.server.model.UserInfo;
import com.notvk.server.model.WallText;
import com.notvk.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class UserPageController {

    @Autowired
    UserPageController(UserService userService) {
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(UserPageController.class);

    private final UserService userService;

    @GetMapping("/")
    public String goToHomePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:login";
        } else {
            UserInfo userByUsername = userService.getUserByUsername(userDetails.getUsername());
            return returnUserPage(model, userByUsername);
        }
    }

    @GetMapping("/id{id}")
    public String goToUserById(@ModelAttribute WallText wallText, @PathVariable("id") long id, Model model, @AuthenticationPrincipal UserDetails userDetails){
        UserInfo user = userService.getUserById(id);
        return returnUserPage(model, user);
    }

    private static String returnUserPage(Model model, UserInfo user) {
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("form", new WallText());
        model.addAttribute("texts", user.getWallText());
        return "HomePage.html";
    }

    @PostMapping("/acceptFormInput")
    public String acceptFormInput(@ModelAttribute WallText wallText, @RequestParam("id") long id, Model model) {
        wallText.setId(null);
        List<WallText> wallTextById = userService.getWallTextById(id);
        wallText.setTime(new Timestamp(new Date().getTime()));
        wallTextById.add(wallText);
        userService.addWallTextById(id, wallText);
        model.addAttribute("form", new WallText());
        model.addAttribute("texts", wallTextById);
        logger.info("ACCEPTED FORM");

        return "redirect:/id" + id;
    }


}
