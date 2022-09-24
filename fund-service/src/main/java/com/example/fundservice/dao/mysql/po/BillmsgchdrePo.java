package com.example.fundservice.dao.mysql.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("billmsgchdre")
public class BillmsgchdrePo implements Serializable {
    private static final long seriaVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long reid;
    private Long chdid;
    private Long cstid;
    private Long accid;
    private Double account;
    private String status;
}
