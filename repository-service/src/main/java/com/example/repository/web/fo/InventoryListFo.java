package com.example.repository.web.fo;

import lombok.Data;

@Data
public class InventoryListFo {
    private Integer pageNum;
    private Integer pageSize;
    private Long typeid;
    private String searchName;
    private Long searchId;
    private String searchUnit;
}
