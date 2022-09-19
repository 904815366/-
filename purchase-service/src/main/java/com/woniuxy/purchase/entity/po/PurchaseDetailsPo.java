package com.woniuxy.purchase.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("purchase_details")
public class PurchaseDetailsPo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Long id;
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
