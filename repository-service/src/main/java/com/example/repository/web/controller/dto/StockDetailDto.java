package com.example.repository.web.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StockDetailDto {
    private String id;
    private Integer type;
    private String goodName;
    private String goodUnit;
    private Integer num;
    private LocalDateTime time;
    private Integer status;
}
