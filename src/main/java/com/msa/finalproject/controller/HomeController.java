package com.msa.finalproject.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    @GetMapping // GET / home()
    public String home() {
        logger.info("GET / home() ......");
        return "home";
    }
}
