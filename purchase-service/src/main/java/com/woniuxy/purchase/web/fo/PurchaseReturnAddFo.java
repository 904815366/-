package com.woniuxy.purchase.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseReturnAddFo implements Serializable {
    @NotNull
    private Long supplierId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime invoiceTime;
    private String invoiceNumber;
    @NotNull
    private Double discount;
    @NotNull
    private BigDecimal saleMoney;
    @NotNull
    private BigDecimal laterMoney;
    @NotNull
    private BigDecimal refundAmount;
    @NotNull
    private Long settlementAccountId;
    @NotNull
    private Double debtMoney;
    @NotNull
    private Long uId;
    @NotNull
    private String auditName;
    private Integer version;
    private Integer status;
    private Integer active;
    @NotNull
    private List<ReturnDetailFo> returnDetailFoList;
    @NotNull(message = "不能为空")
    private String validToken;
}
