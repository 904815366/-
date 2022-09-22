package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDetailFo implements Serializable {
    private Long purchaseId;
    private Long goodsId;
    private double num;
    private double originalMoney;
    private double discount;
    private double saleMoney;
    private double laterMoney;
    private String remark;
    private String version;
    private String status;
}
