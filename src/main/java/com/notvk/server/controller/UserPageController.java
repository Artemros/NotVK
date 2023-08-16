package com.notvk.server.controller;

import com.notvk.server.model.UserInfo;
import com.notvk.server.model.WallText;
import com.notvk.server.repository.UserRepository;
import com.notvk.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserPageController {

    @Autowired
    UserPageController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;


//    @RequestMapping(value = "/id{id}")
//    @ResponseBody
//    public String getUserPage(
//            @PathVariable("id") long id,
//            @ModelAttribute UserInfo userInfo,
//            Model model
//    ) {
////        model.addAttribute("profile", userInfo);
////        Iterable<UserInfo> all = userRepository.findAll();
////        model.addAttribute("texts", all);
//        return "HomePage.html";
//    }
@GetMapping("/id{id}")
public String goToHomePage(@ModelAttribute WallText wallText, @PathVariable("id") long id, Model model) throws IOException {

    model.addAttribute("id",id);
    model.addAttribute("form", new WallText());
//    userService.getUser()
    List<WallText> wallTextById = userService.getWallTextById(id);
    model.addAttribute("texts", wallTextById);
    return "HomePage.html";
}

    @PostMapping("/acceptFormInput")
    public String acceptFormInput(@ModelAttribute WallText wallText, @RequestParam("id") long id, Model model) {
        wallText.setId(null);
        List<WallText> wallTextById = userService.getWallTextById(id);
        wallText.setTime(new java.util.Date().toString());
        wallTextById.add(wallText);
//        Map<Long, WallText> wallTetWithId = new HashMap<>();
//        wallTetWithId.put(Long.getLong(id),new WallText());
        model.addAttribute("form", new WallText());
        model.addAttribute("texts", wallTextById);
        userService.addWallTextById(id, wallText);
        //logger.info("ACCEPTED FORM");
        model.addAttribute("texts", wallTextById);

        return "redirect:/id"+id;
    }



}
