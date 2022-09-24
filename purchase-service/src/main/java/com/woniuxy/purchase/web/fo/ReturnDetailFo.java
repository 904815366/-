package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDetailFo implements Serializable {
    private Long purchaseReturnId;
    private Long goodsId;
    private Integer num;
    private BigDecimal originalMoney;
    private Double discount;
    private BigDecimal laterMoney;
    private String purchaseNumber;
    private String remark;
    private Integer status;
    private BigDecimal saleMoney;
}
