package com.notvk.NotVK.homepage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomePageController {
    @Autowired
    public HomePageController(WallTextRepository wallTextRepository) {
        this.wallTextRepository = wallTextRepository;
    }

    private Map<Long, WallText> wallTextMap = new HashMap<>();

    private WallTextRepository wallTextRepository;

    Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @GetMapping("/")
    public String goToHomePage(@ModelAttribute WallText wallText, Model model) throws IOException {
        model.addAttribute("form", new WallText());
        model.addAttribute("texts", wallTextRepository);
        Iterable<WallText> all = wallTextRepository.findAll();
        model.addAttribute("texts", all);
        return "HomePage.html";
    }

    @PostMapping("/acceptFormInput")
    public String acceptFormInput(@ModelAttribute WallText wallText, Model model) {
        model.addAttribute("form", new WallText());
        model.addAttribute("texts", wallTextRepository);
        wallTextRepository.save(wallText);
        Iterable<WallText> all = wallTextRepository.findAll();
        logger.info("ACCEPTED FORM");
        model.addAttribute("texts", all);
        return "redirect:/";
    }

}
