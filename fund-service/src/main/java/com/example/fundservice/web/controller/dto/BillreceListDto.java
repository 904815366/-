package com.example.fundservice.web.controller.dto;

import lombok.Data;

import java.util.Date;
@Data
public class BillreceListDto {
    private Integer sno;//单据编号
    private Date stime;//单据日期
    private Integer cstname;//客户
    private Double saccount;//收款金额
    private Integer username;//制单人
    private String sdecr;//备注
}
