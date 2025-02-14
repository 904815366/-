package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class AddShipmentFo {

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

    private List<GoodsFo> goodsFos;
}
