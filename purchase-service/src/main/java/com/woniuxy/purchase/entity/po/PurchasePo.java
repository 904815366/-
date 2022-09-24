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
@TableName("purchase")
public class PurchasePo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime invoiceTime;
    private String invoiceNumber;
    private Long supplierId;
    private BigDecimal discount;
    private BigDecimal saleMoney;
    private BigDecimal laterMoney;
    private BigDecimal practicalMoney;
    private BigDecimal debtMoney;
    private Long settlementAccountId;
    private Integer paymentStatus;
    private Integer status;
    private Long uId;
    private String auditName;
    private Integer version;
}
