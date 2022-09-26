package com.example.repository.web.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StockDetailDto {
    private String id;
    private Integer type;
    private Long goodsid;
    private Integer num;
    private LocalDateTime time;
//    private
}
