package com.msa.finalproject.service;

import com.msa.finalproject.mapper.ResearchMapper;
import com.msa.finalproject.model.*;
import com.msa.finalproject.util.Statics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ResearchServiceImpl implements ResearchService {

    private final ResearchMapper researchMapper;

    private final int PAGE_SIZE = Statics.PAGE_SIZE.getValue();

    @Autowired
    public ResearchServiceImpl(ResearchMapper researchMapper) {
        this.researchMapper = researchMapper;
    }

    @Override
    public RSsDTO getList(RequestRSDTO requestRSDTO) {
        String column = requestRSDTO.getColumn();
        String keyword = requestRSDTO.getKeyword();
        Integer totalSeq = researchMapper.getTotalSeq(column, keyword);

        if (requestRSDTO.getPage() == null || (!(requestRSDTO.getPageToGo() == null) &&requestRSDTO.getPageToGo().equals("1"))) {
            List<RS> rss = researchMapper.getListWithOnlyPageSize(PAGE_SIZE, column, keyword);
            if (rss.isEmpty()) {
                return null;
            }
            RSsDTO rssDTO = new RSsDTO(
                    rss.stream()
                            .map(RSDTO::new)
                            .toList(), totalSeq, 1);
            rssDTO.setKeyword(keyword);
            rssDTO.setColumn(column);
            return rssDTO;
        }
        int page = Integer.parseInt(requestRSDTO.getPage());
        int pageToGo = Integer.parseInt(requestRSDTO.getPageToGo());
//        현재 페이지가 1일때만 startSeq 와 endSeq 가 null 이다.
        int startSeq = Integer.parseInt(requestRSDTO.getStartBoard());
        int endSeq = Integer.parseInt(requestRSDTO.getEndBoard());

        List<RS> rss;
        int offset;
        if (page == pageToGo) {
            rss = researchMapper.getRSsFromEnd(PAGE_SIZE, startSeq + 1, 0,column, keyword);
        } else if (page > pageToGo) {
            offset = ((pageToGo - page) - 1) * PAGE_SIZE;
            rss = researchMapper.getRSsFromEnd( PAGE_SIZE,endSeq,offset, column, keyword);
        } else {
            offset = ((page - pageToGo) - 1) * PAGE_SIZE;
            rss = researchMapper.getRSsFromStart(PAGE_SIZE,startSeq,offset, column, keyword);
            Collections.reverse(rss);
        }

        RSsDTO rssDTO = new RSsDTO(
                rss.stream()
                        .map(RSDTO::new)
                        .toList(), totalSeq, pageToGo);
        rssDTO.setKeyword(keyword);
        rssDTO.setColumn(column);
        return rssDTO;
    }

    @Override
    public void createRS(RSDTO rsDTO) {
        researchMapper.createRS(rsDTO);
        List<RSI> suri = rsDTO.getSuri();
        for (RSI _suri : suri) {
            _suri.setSur_seq(rsDTO.getSur_seq());
            researchMapper.createRSI(_suri);
        }
    }
}
