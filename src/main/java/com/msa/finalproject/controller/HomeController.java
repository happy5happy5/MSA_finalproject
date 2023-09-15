package com.msa.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping // GET / home()
    public String home() {
        System.out.println("[HOME CONTROLLER] GET / home() ......");
        return "home";
    }
}
