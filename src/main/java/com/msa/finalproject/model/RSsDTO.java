package com.msa.finalproject.model;

import com.msa.finalproject.util.Statics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RSsDTO {


    private @NotNull List<RSDTO> RSDTO;
    private Integer page;
    //    private Integer pageToGo;
    private Integer totalPage;
    private Integer totalSeq;
    private Integer startPage;
    private Integer endPage;
    private Integer startSeq;
    private Integer endSeq;
    private Integer prevPageBlock;
    private Integer nextPageBlock;
    private Integer pageBlock;
    private Integer pageBlockStart;
    private Integer pageBlockEnd;
    private String column;
    private String keyword;
    final Integer BS = Statics.BLOCK_SIZE.getValue();
    final Integer PS = Statics.PAGE_SIZE.getValue();

    public RSsDTO(@NotNull List<RSDTO> rsDTO, @NotNull Integer totalSeq, @NotNull Integer pageToGo) {
        this.RSDTO = rsDTO;
        this.page = pageToGo;
        this.totalSeq = totalSeq;
        this.totalPage = (totalSeq - 1) / PS + 1;
        this.startPage = (page - 1) / BS * BS + 1;
        this.endPage = Math.min(startPage + BS - 1, totalPage);
        this.startSeq = rsDTO.get(0).getSur_seq();
        this.endSeq = rsDTO.get(rsDTO.size() - 1).getSur_seq();
        this.prevPageBlock = Math.max(startPage - BS, 1);
        this.nextPageBlock = Math.min(endPage + BS, totalPage);
        this.pageBlock = (page - 1) / BS + 1;
        this.pageBlockStart = (pageBlock - 1) * BS + 1;
        this.pageBlockEnd = Math.min(pageBlockStart + BS - 1, totalPage);
        int no = totalSeq - (page - 1) * PS;
        for (RSDTO _rsDTO : rsDTO) _rsDTO.setNo(no--);
    }
}
