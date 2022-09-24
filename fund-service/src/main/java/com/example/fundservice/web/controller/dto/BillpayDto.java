package com.example.fundservice.web.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BillpayDto {
    private String gysname;//供应商
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ftime;//单据日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date latime;
    private Long fno;//单据编号
    private String accname;//结算账户
    private Double faccount;//收款金额
    private String paytype;//结算方式
    private String fdecr;//备注
    private String username;//制单人
}
