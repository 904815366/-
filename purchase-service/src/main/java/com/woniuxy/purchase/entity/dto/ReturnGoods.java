package com.woniuxy.purchase.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnGoods implements Serializable {
    private Integer id;
    private String goodsName;
    private String unit;
    private Integer num;
    private BigDecimal originalMoney;
    private Double discount;
    private BigDecimal saleMoney;
    private BigDecimal laterMoney;
    private String purchaseNumber;
    private String remark;
}
