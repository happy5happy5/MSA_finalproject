package com.msa.finalproject.service;

import com.msa.finalproject.mapper.ResearchMapper;
import com.msa.finalproject.model.*;
import com.msa.finalproject.config.Statics;
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

        if (requestRSDTO.getPage() == null || (!(requestRSDTO.getPageToGo() == null) && requestRSDTO.getPageToGo().equals("1"))) {
            List<RS> rss = researchMapper.getRSsWithOnlyPageSize(PAGE_SIZE, column, keyword);
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
        int startSeq = Integer.parseInt(requestRSDTO.getStartSeq());
        int endSeq = Integer.parseInt(requestRSDTO.getEndSeq());

        List<RS> rss;
        int offset;
        if (page == pageToGo) {
            rss = researchMapper.getRSsFromEnd(PAGE_SIZE, startSeq + 1, 0, column, keyword);
        } else if (page < pageToGo) {
            offset = ((pageToGo - page) - 1) * PAGE_SIZE;
            rss = researchMapper.getRSsFromEnd(PAGE_SIZE, endSeq, offset, column, keyword);
        } else {
            offset = ((page - pageToGo) - 1) * PAGE_SIZE;
            rss = researchMapper.getRSsFromStart(PAGE_SIZE, startSeq, offset, column, keyword);
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

    @Override
    public List<RSA> getRSA(int surSeq) {
        return researchMapper.getRSA(surSeq);
    }

    @Override
    public void sendSurveyResult() {
//        use_yn 이 n 인 rs 을 모두 가져온다.
//        rsr 을 계산 한다.
//        rsr 을 insert 하고 rs 의 use_yn 을 y 로 바꾼다. 오직 스케줄러 가 할 일이다.

        List<RS> rss=researchMapper.getRSsWhereUseYNIsN();
        for(RS rs:rss){
            int surSeq= rs.getSur_seq();
            List<RSA> rsa=researchMapper.getRSA(surSeq);
//            각 rsa 의 sura_item 당 각각 의 합을 구한다.
//            일단 sura_item 의 형태인 '123' 을 1,2,3 으로 나눈다.
//            sura_item 을 나눈걸 item 이라고 하자.
//            item 을 하나씩 꺼내서 sura_no 를 구한다.
//            sura_no 에 해당하는 suri_seq 를 구한다.
//            suri_seq 에 해당하는 rsr 을 없다면 insert 하고 있다면 update 한다
//            그리고 rsa 의 sura_item 을 각각의 합으로 바꾼다.
//            그리고 rsa 의 sura_cnt 를 구한다.

            for(RSA _rsa:rsa){
                String suraItem=_rsa.getSura_item();
                String[] suraItems=suraItem.split("");
                int suraCnt=0;
                for(String item:suraItems){
//                    Integer.parseInt(item) 이 정답의 번호이다.
                    int suraNo=Integer.parseInt(item);
//                    suraNo 에 해당하는 suri_seq 를 구한다.
//                    int suriSeq=researchMapper.getSuriSeq(surSeq,suraNo);
                }
//                _rsa.setSura_cnt(suraCnt);
//                researchMapper.editRSA(_rsa);
            }


            int surrCnt=rsa.size();



        }
    }
}
