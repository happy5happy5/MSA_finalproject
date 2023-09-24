package com.msa.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RSDTO {
    private Integer no;
    private Integer sur_seq;
    private String sur_title;
    private String sur_desc;
    private Integer que_cnt;
    private Integer hits;
    private String sur_sat_date;
    private String sur_end_date;
    private String reg_name;
    private String reg_date;
    private String use_yn;
    private String udt_name;
    private String udt_date;
    private String useable;
    private String editable;
    private List<RSI> suri;
    private List<String> deletedQueryId;


    public RSDTO(RS rs) {
        this.no = rs.getNo();
        this.sur_seq = rs.getSur_seq();
        this.sur_title = rs.getSur_title();
        this.sur_desc = rs.getSur_desc();
        this.que_cnt = rs.getQue_cnt();
        this.hits = rs.getHits();
        this.sur_sat_date = rs.getSur_sat_date();
        this.sur_end_date = rs.getSur_end_date();
        this.reg_name = rs.getReg_name();
        this.reg_date = rs.getReg_date();
        this.udt_name = rs.getUdt_name();
        this.udt_date = rs.getUdt_date();
//        날짜 비교 (비교 형태는 yyyy-MM-dd T HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.parse(rs.getSur_sat_date(), formatter);
        LocalDateTime end = LocalDateTime.parse(rs.getSur_end_date(), formatter);
        if (now.isBefore(start)) {
            this.useable = "N";
            this.editable = "Y";
        } else if (now.isAfter(end)) {
            this.useable = "N";
            this.editable = "N";
        } else {
            this.useable = "Y";
            this.editable = "N";
        }

    }

    public RSDTO(RS rs, List<RSI> suri) {
        this.no = rs.getNo();
        this.sur_seq = rs.getSur_seq();
        this.sur_title = rs.getSur_title();
        this.sur_desc = rs.getSur_desc();
        this.que_cnt = rs.getQue_cnt();
        this.hits = rs.getHits();
        this.sur_sat_date = rs.getSur_sat_date();
        this.sur_end_date = rs.getSur_end_date();
        this.reg_name = rs.getReg_name();
        this.reg_date = rs.getReg_date();
        this.suri = suri;
        //        날짜 비교 (비교 형태는 yyyy-MM-dd T HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.parse(rs.getSur_sat_date(), formatter);
        LocalDateTime end = LocalDateTime.parse(rs.getSur_end_date(), formatter);
        if (now.isBefore(start)) {
            this.useable = "N";
            this.editable = "Y";
        } else if (now.isAfter(end)) {
            this.useable = "N";
            this.editable = "N";
        } else {
            this.useable = "Y";
            this.editable = "N";
        }
    }


}
