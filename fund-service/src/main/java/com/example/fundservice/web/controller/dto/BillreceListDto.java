package com.example.fundservice.web.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class BillreceListDto {
    private Long sno;//单据编号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stime;//单据日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date latime;
    private String cstname;//客户
    private Double saccount;//收款金额
    private String username;//制单人
    private String sdecr;//备注
}
