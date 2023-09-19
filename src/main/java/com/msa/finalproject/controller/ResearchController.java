package com.msa.finalproject.controller;

import com.msa.finalproject.model.*;
import com.msa.finalproject.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/research")
public class ResearchController {

    private final ResearchService researchService;

    @Autowired
    public ResearchController(ResearchService researchService) {
        this.researchService = researchService;
    }

    @GetMapping({"/","/list"})
    public String research(Model model, RequestRSDTO requestRSDTO) {
        System.out.println("[ResearchController] GET /research research()");

        RSsDTO rssDTO = researchService.getList(requestRSDTO);
        model.addAttribute("rssDTO", rssDTO);
        if (rssDTO == null) {
            model.addAttribute("redirectURL", "/research/list");
            System.out.println("[ResearchController] GET /research research() noresult");
            return "noresult";
        }
        return "research/listform";
    }

    @GetMapping("/create")
    public String create(Model model) {
        System.out.println("[ResearchController] GET /research/create create()");
        RSDTO rsDTO = new RSDTO();
        model.addAttribute("rsDTO", rsDTO);
        return "research/createform";
    }

    @PostMapping("/create")
    public String create(@RequestBody RSDTO rsDTO) {
        System.out.println("[ResearchController] POST /research/create create()");

        researchService.createRS(rsDTO);


        return "redirect:/research/list";
    }

}
