package com.woniu.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ShipmentDto {
    private Long id;

    private Long clorderId;

    private Long umoneyId;

    private Double payeemoney;

    private Double dept;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date attime;

    private String status;

    private String version;

    private Double discount;

    private Double saleMoney;

    private Double laterMoney;

    private String name;
}
