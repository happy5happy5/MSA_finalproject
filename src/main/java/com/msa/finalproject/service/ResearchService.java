package com.msa.finalproject.service;

import com.msa.finalproject.model.*;

import java.util.List;

public interface ResearchService {

    RSsDTO getList(RequestRSDTO requestRSDTO);


    void createRS(RSDTO rsDTO);

    RSDTO getRS(int surSeq);

    void editRS(RSDTO rsDTO);

    void createRSA(List<RSA> rsaDTO);
}
