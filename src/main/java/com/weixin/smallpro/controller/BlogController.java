package com.weixin.smallpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}
