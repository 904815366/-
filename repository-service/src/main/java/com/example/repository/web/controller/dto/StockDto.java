package com.example.repository.web.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockDto {
    private Long id;
    private Long goodsid; //商品编号
    private String goodsName;  //商品名
    private String typeName; //商品分类
    private String size;  //规格型号
    private Integer stock;  //系统库存
    private String unit;  //单位
    private Integer innum; //入库数量
    private Integer outnum; //出库数量
}
