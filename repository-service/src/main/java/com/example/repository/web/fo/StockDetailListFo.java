package com.example.repository.web.fo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDetailListFo {
    private Long goodsid;
    private Integer status;
    private Integer pageNum;
    private Integer pageSize;
}
