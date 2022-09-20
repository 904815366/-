package com.example.fundservice.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("billrece")
public class BillrecePo implements Serializable {

    private static final long seriaVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long sno;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stime;
    private Long chdid;
    private Long cstid;
    private Long accid;
    private Double saccount;
    private Long userid;
    private String sdecr;
    private String sstatus;
}
