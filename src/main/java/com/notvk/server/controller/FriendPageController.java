package com.notvk.server.controller;

import com.notvk.server.model.SearchedInfo;
import com.notvk.server.model.UserInfo;
import com.notvk.server.model.WallText;
import com.notvk.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FriendPageController {

    private final UserService userService;

    @Autowired
    public FriendPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/friends")
    public String displayMyFriends(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:register";
        } else {
            List<UserInfo> friendList = userService.getUserByUsername(userDetails.getUsername()).getFriendList();
            return returnUserPage(model);
        }
    }

    private static String returnUserPage(Model model) {
        model.addAttribute("form", new SearchedInfo());


        return "Friends.html";
    }

    @GetMapping("/find-friends")
    public String findFriends(Model model, @AuthenticationPrincipal UserDetails userDetails , @ModelAttribute SearchedInfo searchedInfo) {
        if (userDetails == null) {
            return "redirect:register";
        } else {
            return "Friends.html";
        }
    }

    @PostMapping("/find-friends")
    public String dsf(Model model, @ModelAttribute SearchedInfo searchedInfo) {
        model.addAttribute("form", new SearchedInfo());
        model.addAttribute("foundNames", userService.findByName(searchedInfo.getSearched()));
        return "redirect:friends";
    }
}
