package com.woniuxy.purchase.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("purchase_return_details")
public class PurchaseReturnDetailsPo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long purchaseReturnId;
    private Long goodsId;
    private Integer num;
    private BigDecimal originalMoney;
    private Double discount;
    private BigDecimal laterMoney;
    private String purchase_number;
    private String remark;
    private Integer status;
}
