package com.woniuxy.purchase.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("PurchaseReturnDto")
public class PurchaseReturnDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String invoiceTime;
    private String invoiceNumber;
    private String supplierName;
    private Double discount;
    private BigDecimal saleMoney;
    private BigDecimal laterMoney;
    private String account;
    private BigDecimal debtMoney;
    private BigDecimal refundAmount;
    private String username;
    private String auditName;
    private List<ReturnGoods> goodsList;
}
