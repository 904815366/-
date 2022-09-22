package com.woniu.repository.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("cusorder")
public class CusorderDto {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordertime;

    private String sitename;

    private String phone;

    private String site;

    private Double money;

    private String busibess;

    private Long cusId;

    private String status;

    private Long uId;

    private String prepar;

    private String auditor;

    private String version;

//    private String name;
}
