package com.woniuxy.purchase.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseReturnList {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime invoiceTime;
    private String invoiceNumber;
    private String supplierName;
    private BigDecimal saleMoney;
    private BigDecimal laterMoney;
    private BigDecimal refundAmount;
    private String username;
    private String auditName;
}
