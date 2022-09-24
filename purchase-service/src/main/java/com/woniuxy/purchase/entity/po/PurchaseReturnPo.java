package com.woniuxy.purchase.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("purchase_return")
public class PurchaseReturnPo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Long id;
    private Long supplierId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime invoiceTime;
    private String invoiceNumber;
    private Double discount;
    private BigDecimal saleMoney;
    private BigDecimal laterMoney;
    private BigDecimal refundAmount;
    private Long settlementAccountId;
    private Double debtMoney;
    private Long uId;
    private String auditName;
    private Integer version;
    private Integer status;
    private Integer active;
    private Integer paymentStatus;
}
