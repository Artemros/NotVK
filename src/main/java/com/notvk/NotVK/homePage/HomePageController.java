package com.notvk.NotVK.homePage;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    private final HomePageService homePageService;

    @Autowired
    public HomePageController(HomePageService homePageService){
        this.homePageService=homePageService;
    }

//    @GetMapping("/")
//    public String


}
