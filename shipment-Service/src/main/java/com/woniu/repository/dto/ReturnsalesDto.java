package com.woniu.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ReturnsalesDto {
    private Long id;

    private Long shipmentId;

//    private String detailId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returntime;

    private Double returnMoney;

    private String status;

    private Long uid;

    private String version;

//    客户名字
    private String cusname;
}
