package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseReturnDeleteByIds implements Serializable {
    @NotNull(message = "不能为空")
    private String ids;
    @NotNull(message = "不能为空")
    private String validToken;

    public Long[] getIds(){
        String[] split = ids.split(",");
        Long[] array=new Long[split.length];
        for (int i = split.length - 1; i >= 0; i--) {
            array[i]=Long.parseLong(split[i]);
        }
        return array;
    }
}
