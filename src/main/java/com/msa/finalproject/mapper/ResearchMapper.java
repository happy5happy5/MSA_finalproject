package com.msa.finalproject.mapper;

import com.msa.finalproject.model.RS;
import com.msa.finalproject.util.ResearchSQLProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

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


}
