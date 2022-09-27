package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.dao.po.CusOrderDetail;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ReturnsalesFo {
// 出货单编号
    private Long shipmentId;

//    private String detailId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returntime;

    private Double returnMoney;

    private String status;

    private Long uid;

    private String version;

    private Long accid;//账户id

//    修改订单详情的数据
    private List<CusOrderDetail> cusOrderDetails;
}
