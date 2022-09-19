package com.woniuxy.purchase.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseList implements Serializable {
    private Long id;
    //采购订单时间
    private Timestamp invoiceTime;
    //采购订单编号
    private String invoiceNumber;
    //供应商
    private String supplierName;
    //优惠金额
    private BigDecimal saleMoney;
    //优惠后金额
    private BigDecimal laterMoney;
    //实付金额
    private BigDecimal practicalMoney;
    //制单人
    private String username;
    //审核人
    private String auditName;
}
