package com.zlab.commune.api.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //create a mapping for /hello

    @GetMapping("/hello")
    public String hello(Model model){

        model.addAttribute("theDate", new java.util.Date());

        return "helloworld";
    }
}
