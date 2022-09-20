package com.example.fundservice.web.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BillreceDto {
    private String cstname;//客户
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stime;//单据日期
    private Long sno;//单据编号
    private String accname;//结算账户
    private Double saccount;//收款金额
    private String paytype;//结算方式
    private String sdecr;//备注
    private String username;//制单人
}
