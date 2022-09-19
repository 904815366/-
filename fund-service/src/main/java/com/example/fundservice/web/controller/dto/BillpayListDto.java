package com.example.fundservice.web.controller.dto;

import lombok.Data;

import java.util.Date;
@Data
public class BillpayListDto {
    private Integer fno;//单据编号
    private Date ftime;//单据日期
    private Integer gysname;//供应商
    private Double faccount;//收款金额
    private Integer username;//制单人
    private String fdecr;//备注
}
