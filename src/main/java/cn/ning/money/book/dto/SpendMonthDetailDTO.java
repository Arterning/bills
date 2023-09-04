package cn.ning.money.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class SpendMonthDetailDTO {

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date month;

    /**
     * 支出总额
     */
    private Double expendTotal;

    /**
     * 收入总额
     */
    private Double incomeTotal;
}
