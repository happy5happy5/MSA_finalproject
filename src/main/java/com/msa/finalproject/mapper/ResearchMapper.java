package com.msa.finalproject.mapper;

import com.msa.finalproject.model.RS;
import com.msa.finalproject.model.RSDTO;
import com.msa.finalproject.model.RSI;
import com.msa.finalproject.util.ResearchSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResearchMapper {

    @SelectProvider(type = ResearchSQLProvider.class, method = "getListWithOnlyPageSize")
    List<RS> getListWithOnlyPageSize(int pageSize, String column, String keyword);

    @SelectProvider(type = ResearchSQLProvider.class, method = "getTotalSeq")
    Integer getTotalSeq(String column, String keyword);

    @SelectProvider(type = ResearchSQLProvider.class, method = "getRSsFromEnd")
    List<RS> getRSsFromEnd(
            int pageSize,
            int endSeq,
            int offSet,
            String column,
            String keyword
    );

    @SelectProvider(type = ResearchSQLProvider.class, method = "getRSsFromStart")
    List<RS> getRSsFromStart(
            int pageSize,
            int startSeq,
            int offset,
            String column,
            String keyword
    );


    @Insert("INSERT INTO rs (sur_title, sur_desc, que_cnt, sur_sat_date, sur_end_date, reg_name, reg_date, use_yn, udt_name, udt_date) " +
            "VALUES (#{sur_title}, #{sur_desc}, #{que_cnt}, #{sur_sat_date}, #{sur_end_date}, #{reg_name}, #{reg_date}, #{use_yn}, #{udt_name}, #{udt_date})")
    @Options(useGeneratedKeys = true, keyProperty = "sur_seq")
    void createRS(RSDTO rsDTO);

    @Insert("INSERT INTO rsi (suri_no,sur_seq, suri_seq, suri_title,suri_que1, suri_que2, suri_que3, suri_que4, suri_que5, suri_type, suri_multi, suri_etc, reg_name, reg_date, udt_name, udt_date) " +
            "VALUES (#{suri_no},#{sur_seq}, #{suri_seq}, #{suri_title}, #{suri_que1}, #{suri_que2}, #{suri_que3}, #{suri_que4}, #{suri_que5}, #{suri_type}, #{suri_multi}, #{suri_etc}, #{reg_name}, #{reg_date}, #{udt_name}, #{udt_date})")
    @Options(useGeneratedKeys = true, keyProperty = "suri_seq")
    void createRSI(RSI suri);
}
