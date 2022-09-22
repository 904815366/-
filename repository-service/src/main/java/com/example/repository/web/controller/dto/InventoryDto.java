package com.example.repository.web.controller.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class InventoryDto{
    private Long id;
    private Long goodsid;
    private String goodsName;  //商品名
    private String typeName; //商品分类
    private String size;  //规格型号
    private Integer stock;  //商品库存
    private String unit;  //单位
    private Integer stocktake;
    private Integer profitloss;
    private Integer status;
    private String remark;
}
