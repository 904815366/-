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

@NoArgsConstructor
@AllArgsConstructor
@Data
public class  PurchaseModifyFo implements Serializable {
    @NotNull(message = "不能为空")
    private Long id;
    @NotNull(message = "不能为空")
    private Integer supplierId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime invoiceTime;
    @NotNull(message = "不能为空")
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
