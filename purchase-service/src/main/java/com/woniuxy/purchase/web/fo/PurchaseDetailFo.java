package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.directory.SearchResult;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailFo implements Serializable {
    private Long purchaseId;
    private Long goodsId;
    private Integer num;
    private BigDecimal originalMoney;
    private Double discount;
    private BigDecimal saleMoney;
    private BigDecimal laterMoney;
    private String remark;
    private Integer version;
    private Integer status;
}
