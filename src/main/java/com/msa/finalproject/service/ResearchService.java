package com.msa.finalproject.service;

import com.msa.finalproject.model.RS;
import com.msa.finalproject.model.RSDTO;
import com.msa.finalproject.model.RSsDTO;
import com.msa.finalproject.model.RequestRSDTO;

import java.util.List;

public interface ResearchService {

    RSsDTO getList(RequestRSDTO requestRSDTO);


    void createRS(RSDTO rsDTO);
}
