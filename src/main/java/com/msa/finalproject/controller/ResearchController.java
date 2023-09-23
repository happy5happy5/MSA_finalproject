package com.msa.finalproject.controller;

import com.msa.finalproject.model.*;
import com.msa.finalproject.service.ResearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/research")
public class ResearchController {


    private final ResearchService researchService;

    private final Logger logger = LoggerFactory.getLogger(ResearchController.class);

    @Autowired
    public ResearchController(ResearchService researchService) {
        this.researchService = researchService;
    }

    @GetMapping({"/","/list"})
    public String research(Model model, RequestRSDTO requestRSDTO) {
        logger.info("GET /research/list research()");
        RSsDTO rssDTO = researchService.getList(requestRSDTO);
        model.addAttribute("rssDTO", rssDTO);
        if (rssDTO == null) {
            model.addAttribute("redirectURL", "/research/list");
            logger.info("[ResearchController] GET /research research() noresult page");
            return "noresult";
        }
        return "research/listform";
    }

    @GetMapping("/create")
    public String create(Model model) {
        logger.info("GET /research/create create()");
        RSDTO rsDTO = new RSDTO();
        model.addAttribute("rsDTO", rsDTO);
        return "research/createform";
    }

    @PostMapping("/create")
    public String create(@RequestBody RSDTO rsDTO) {
        logger.info("POST /research/create create()");
        researchService.createRS(rsDTO);
        return "redirect:/research/list";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam int sur_seq) {
        logger.info("GET /research/detail detail()");
        RSDTO rsDTO = researchService.getRS(sur_seq);
        model.addAttribute("rsDTO", rsDTO);
        return "research/readform";
    }


    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int sur_seq) {
        logger.info("GET /research/edit edit()");
        RSDTO rsDTO = researchService.getRS(sur_seq);
        model.addAttribute("rsDTO", rsDTO);
        return "research/editform";
    }

    @PostMapping("/edit")
    public String edit(@RequestBody RSDTO rsDTO) {
        logger.info("POST /research/edit edit()");
        researchService.editRS(rsDTO);
        return "redirect:/research/list";
    }

    @GetMapping("/start")
    public String start(Model model, @RequestParam int sur_seq) {
        logger.info("GET /research/start start()");
        RSDTO rsDTO = researchService.getRS(sur_seq);
        model.addAttribute("rsDTO", rsDTO);
        return "research/startform";
    }

    @PostMapping("/start")
    public String start(@RequestBody List<RSA> rsaDTO) {
        logger.info("POST /research/start start()");
        researchService.createRSA(rsaDTO);
        return "redirect:/research/list";
    }


    @GetMapping("/result")
    public String result(Model model, @RequestParam int sur_seq) {
        logger.info("GET /research/result result()");
        RSDTO rsDTO = researchService.getRS(sur_seq);
        List<RSA> rsaDTO = researchService.getRSA(sur_seq);
        model.addAttribute("rsDTO", rsDTO);
        model.addAttribute("rsaDTO", rsaDTO);
        return "research/resultform";
    }

}
