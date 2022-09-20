package com.example.fundservice.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.formatter.qual.Format;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("billpay")
public class BillpayPo implements Serializable {
    private static final long seriaVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long fno;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ftime;
    private Long cgdid;
    private Long gysid;
    private Long accid;
    private Double faccount;
    private Long userid;
    private String fdecr;
    private String fstatus;
}
