package com.woniuxy.purchase.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("PurchaseDetail")
public class PurchaseDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    //采购订单时间
    private String invoiceTime;
    //采购订单编号
    private String invoiceNumber;
    //供应商
    private String supplierName;
    //优惠金额
    private BigDecimal saleMoney;
    //优惠后金额
    private BigDecimal laterMoney;

    private List<Goods> goods;
    //实付金额
    private BigDecimal practicalMoney;
    //制单人
    private String username;
    //审核人
    private String auditName;
    //付款状态
    private String paymentStatus;
}
