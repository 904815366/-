package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDeleteById {
    @NotNull(message = "不能为空")
    private Long id;
    @NotNull(message = "不能为空")
    private String validToken;
}
