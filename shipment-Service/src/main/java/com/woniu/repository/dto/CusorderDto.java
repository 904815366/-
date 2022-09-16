package com.woniu.repository.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("cusorder")
public class CusorderDto {

    private Long id;

    private Date ordertime;

    private String sitename;

    private String phone;

    private String site;

    private Double money;

    private String busibess;

    private Long cusId;

    private String statu;

    private Long uId;

    private String prepar;

    private String auditor;

    private String version;

    private String name;
}
