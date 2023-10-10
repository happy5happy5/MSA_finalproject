package com.msa.finalproject.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RS {

    private Integer no;
    private Integer sur_seq;
    private String sur_title;
    private String sur_desc;
    private Integer que_cnt;
    private String sur_sat_date;
    private String sur_end_date;
    private String use_yn;
    private Integer hits;
    private String reg_name;
    private String reg_date;
    private String udt_name;
    private String udt_date;


}
