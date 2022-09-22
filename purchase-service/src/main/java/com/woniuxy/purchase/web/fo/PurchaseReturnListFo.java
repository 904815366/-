package com.woniuxy.purchase.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReturnListFo {
    //采购订单编号
    private String invoiceNumber;
    //审核状态
    private Integer status;
    //查询开始时间
    private LocalDateTime startDateTime;
    //查询结束时间
    private LocalDateTime lastDateTiem;
    @NotNull(message = "参数不能为空")
    private Integer pageSzie;
    @NotNull(message = "参数不能为空")
    private Integer pageNum;
}
