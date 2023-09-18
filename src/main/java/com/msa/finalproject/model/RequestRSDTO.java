package com.msa.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRSDTO {
    private String page;
    private String startBoard;
    private String endBoard;
    private String pageToGo;
    private String column;
    private String keyword;

    public String getColumn(){
        if (this.column == null) return "";
        return this.column;
    }
    public String getKeyword(){
        if (this.keyword == null) return "";
        return this.keyword;
    }
    public void setColumn(String column) {
        if(column == null) this.column = "";
        else this.column = column;
    }

    public void setKeyword(String keyword) {
        if(keyword == null) this.keyword = "";
        else this.keyword = keyword;
    }

}
