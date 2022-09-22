package com.example.repository.dao.mysql.po;

import lombok.Data;

@Data
public class InventoryEditPo {
    private Long id;
    private Integer stocktake;
    private String remark;
}
