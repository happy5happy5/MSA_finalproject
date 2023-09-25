package com.msa.finalproject.config;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ResearchSQLProvider {

    public String getRSsWithOnlyPageSize(@Param("pageSize") int pageSize, @Param("column") String column, @Param("keyword") String keyword) {
        return new SQL() {{
            SELECT("*");
            FROM("rs");
            if ("sur_title".equals(column)) {
                WHERE("sur_title LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("reg_name".equals(column)) {
                WHERE("reg_name LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("sur_content".equals(column)) {
                WHERE("content LIKE CONCAT('%', #{keyword}, '%')");
            }
            ORDER_BY("sur_seq DESC");
            LIMIT(pageSize);
        }}.toString();
    }

    public String getTotalSeq(@Param("column") String column, @Param("keyword") String keyword) {
        return new SQL() {{
            SELECT("COUNT(*)");
            FROM("rs");
            if ("sur_title".equals(column)) {
                WHERE("sur_title LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("reg_name".equals(column)) {
                WHERE("reg_name LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("sur_content".equals(column)) {
                WHERE("content LIKE CONCAT('%', #{keyword}, '%')");
            }
        }}.toString();
    }

    public String getRSsFromEnd(
            @Param("pageSize") int pageSize,
            @Param("endSeq") int endSeq,
            @Param("offSet") int offSet,
            @Param("column") String column,
            @Param("keyword") String keyword
    ) {
        return new SQL() {{
            SELECT("*");
            FROM("rs");
            if ("sur_title".equals(column)) {
                WHERE("sur_title LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("reg_name".equals(column)) {
                WHERE("reg_name LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("sur_content".equals(column)) {
                WHERE("content LIKE CONCAT('%', #{keyword}, '%')");
            }
            WHERE("sur_seq < #{endSeq}");
            ORDER_BY("sur_seq DESC");
            LIMIT(pageSize);
            OFFSET(offSet);
        }}.toString();
    }

    public String getRSsFromStart(
            @Param("pageSize") int pageSize,
            @Param("startSeq") int startSeq,
            @Param("offSet") int offSet,
            @Param("column") String column,
            @Param("keyword") String keyword
    ) {
        return new SQL() {{
            SELECT("*");
            FROM("rs");
            if ("sur_title".equals(column)) {
                WHERE("sur_title LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("reg_name".equals(column)) {
                WHERE("reg_name LIKE CONCAT('%', #{keyword}, '%')");
            } else if ("sur_content".equals(column)) {
                WHERE("content LIKE CONCAT('%', #{keyword}, '%')");
            }
            WHERE("sur_seq > #{startSeq}");
            ORDER_BY("sur_seq");
            LIMIT(pageSize);
            OFFSET(offSet);
        }}.toString();
    }
}
