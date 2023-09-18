package com.msa.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RSDTO {
    private int sur_seq;
    private String sur_title;
    private String sur_desc;
    private int que_cnt;
    private int hits;
    private String sur_sat_date;
    private String sur_end_date;
    private String reg_name;
    private String reg_date;
    private String use_yn;
    private String udt_name;
    private String udt_date;
    private List<RSI> suri=new ArrayList<>();
    private List<String> deletedQueryId;
}
