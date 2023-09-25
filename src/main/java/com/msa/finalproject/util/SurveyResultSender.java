package com.msa.finalproject.util;

import com.msa.finalproject.service.ResearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SurveyResultSender {
    private final ResearchService researchService;
    private static final Logger logger = LoggerFactory.getLogger(SurveyResultSender.class);

    @Autowired
    public SurveyResultSender(ResearchService researchService) {
        this.researchService = researchService;
    }

    @Scheduled(fixedRate = 10000) // 10초마다 실행 (1000 = 1초)
    public void sendSurveyResult() {
        logger.info("sendSurveyResult() 실행");
        researchService.sendSurveyResult();

    }
}
