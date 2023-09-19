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

    @Override
    public RSDTO getRS(int surSeq) {
        RS rs = researchMapper.getRS(surSeq);
        List<RSI> suri = researchMapper.getRSI(surSeq);
        return new RSDTO(rs, suri);
    }

    @Override
    public void editRS(RSDTO rsDTO) {
        researchMapper.editRS(rsDTO);
        List<RSI> suri = rsDTO.getSuri();
        for (RSI _suri : suri) {
            if (_suri.getSuri_seq() == 0) {
                _suri.setSur_seq(rsDTO.getSur_seq());
                researchMapper.createRSI(_suri);
            } else {
                researchMapper.editRSI(_suri);
            }
        }
        List<String> deletedQueryId = rsDTO.getDeletedQueryId();
        if (deletedQueryId != null) {
            for (String id : deletedQueryId) {
                researchMapper.deleteRSI(Integer.parseInt(id));
            }
        }
    }

    @Override
    public void createRSA(List<RSA> rsaDTO) {
        for (RSA _rsa : rsaDTO) {
            researchMapper.createRSA(_rsa);
//            rsr 의 값을 갱신 해야 한다.
//            그냥 여기서 하지 말고 보여 줄때 마다 계산 하는 건 어떰?
//            모두다 종료 되고 나면 한번에 계산 하고 해당하는 모든 rsi 삭제 한다
        }

    }
}
