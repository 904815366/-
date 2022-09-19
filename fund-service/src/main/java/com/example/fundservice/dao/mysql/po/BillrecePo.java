package com.example.fundservice.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("billrece")
public class BillrecePo implements Serializable {

    private static final long seriaVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer sno;
    private Date stime;
    private Integer chdid;
    private Integer cstid;
    private Integer accid;
    private Double saccount;
    private Integer userid;
    private String sdecr;
    private String sstatus;
}
