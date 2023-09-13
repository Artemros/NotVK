package com.notvk.server.controller;

import com.notvk.server.model.UserInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendPageController {
    @GetMapping("/friends")
    public String goToHomePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:register";
        } else {
            return "Friends.html";
        }
    }
}
