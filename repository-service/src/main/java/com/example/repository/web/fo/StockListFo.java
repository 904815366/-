package com.example.repository.web.fo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StockListFo {
    private Integer pageNum;
    private Integer pageSize;
    private Long typeid;
    private String searchName;
}
