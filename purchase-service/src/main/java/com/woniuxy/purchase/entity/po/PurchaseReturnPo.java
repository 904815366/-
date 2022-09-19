package com.woniuxy.purchase.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("purchase_return")
public class PurchaseReturnPo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Long id;
    private String invoiceNumber;
    private Long purchaseDetailsId;
    private double returnMoney;
    private String status;
    private Timestamp returnTime;
    private Long uId;
    private String version;
}
