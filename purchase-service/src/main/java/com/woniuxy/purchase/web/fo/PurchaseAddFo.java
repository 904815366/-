package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseAddFo implements Serializable {
    @NotNull(message = "不能为空")
    private Integer supplierId;
    private LocalDateTime invoiceTime;
    private String invoiceNumber;
    @NotNull(message = "不能为空")
    private List<PurchaseDetailFo> purchaseDetailFoList;
    @NotNull(message = "不能为空")
    private Double discount;
    @NotNull(message = "不能为空")
    private BigDecimal saleMoney;
    @NotNull(message = "不能为空")
    private BigDecimal laterMoney;
    @NotNull(message = "不能为空")
    private BigDecimal practicalMoney;
    @NotNull(message = "不能为空")
    private BigDecimal debtMoney;
    @NotNull(message = "不能为空")
    private Long settlementAccountId;
    @NotNull(message = "不能为空")
    private Integer status;
    @NotNull(message = "不能为空")
    private Long uId;
    @NotNull(message = "不能为空")
    private String auditName;
    @NotNull(message = "不能为空")
    private String validToken;
    private Integer version;
}
