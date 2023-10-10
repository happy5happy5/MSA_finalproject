package com.msa.finalproject.util;

import com.msa.finalproject.mapper.ResearchMapper;
import com.msa.finalproject.model.RS;
import com.msa.finalproject.model.RSDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoadUtility implements CommandLineRunner {

    private final ResearchMapper researchMapper;


        @Override
        public void run(String... args) throws Exception {
            List<RS> rs= researchMapper.getRSsWhereUseYNIsN();
            if(!rs.isEmpty()) return;
            System.out.println("DataLoadUtility.run");
//2023-09-29T04:56 이렇게 표현되는 시간 만들기
            String now = java.time.LocalDateTime.now().toString();
            String[] nows = now.split("T");
            now = nows[0];

            researchMapper.createRS(
                    RSDTO.builder()
                            .sur_title("테스트 설문지 제목")
                            .sur_desc("테스트 설문지 설명")
                            .que_cnt(3)
                            .sur_sat_date(now)
                            .sur_end_date(now)
                            .use_yn("N")
                            .hits(0)
                            .reg_name("관리자")
                            .reg_date(now)
                            .udt_name("관리자")
                            .udt_date(now)
                            .build()
            );
        }
}
