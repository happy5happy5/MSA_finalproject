package com.msa.finalproject.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    public String home() {
        System.out.println("[HOME CONTROLLER] GET / home() ......");
        return "home";
    }
}
