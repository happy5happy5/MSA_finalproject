package com.msa.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RS {

    private Integer no;
    private Integer sur_seq;
    private String sur_title;
    private String sur_desc;
    private String que_cnt;
    private String sur_sat_date;
    private String sur_end_date;
    private String use_yn;
    private String hits;
    private String reg_name;
    private String reg_date;
    private String udt_name;
    private String udt_date;

    public RS(@NotNull RS rs) {
        this.no = rs.getNo();
        this.sur_seq = rs.getSur_seq();
        this.sur_title = rs.getSur_title();
        this.sur_desc = rs.getSur_desc();
        this.que_cnt = rs.getQue_cnt();
        this.sur_sat_date = rs.getSur_sat_date();
        this.sur_end_date = rs.getSur_end_date();
        this.use_yn = rs.getUse_yn();
        this.hits = rs.getHits();
        this.reg_name = rs.getReg_name();
        this.reg_date = rs.getReg_date();
        this.udt_name = rs.getUdt_name();
        this.udt_date = rs.getUdt_date();
    }

public RS toEntity() {
        RS rs = new RS();
        rs.setNo(this.no);
        rs.setSur_seq(this.sur_seq);
        rs.setSur_title(this.sur_title);
        rs.setSur_desc(this.sur_desc);
        rs.setQue_cnt(this.que_cnt);
        rs.setSur_sat_date(this.sur_sat_date);
        rs.setSur_end_date(this.sur_end_date);
        rs.setUse_yn(this.use_yn);
        rs.setHits(this.hits);
        rs.setReg_name(this.reg_name);
        rs.setReg_date(this.reg_date);
        rs.setUdt_name(this.udt_name);
        rs.setUdt_date(this.udt_date);
        return rs;
    }
}
