package com.example.repository.web.fo;

import com.example.repository.dao.mysql.po.InventoryEditPo;
import com.example.repository.dao.mysql.po.InventoryPo;
import lombok.Data;

import java.util.List;

@Data
public class InventoryEditFo {
    private Integer pageNum;
    private Integer pageSize;
    private List<InventoryEditPo> poList;
}
