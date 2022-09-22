package com.example.repository.web.controller;
import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.po.InventoryEditPo;
import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.service.InventoryService;
import com.example.repository.util.ResponseResult;
import com.example.repository.web.controller.dto.InventoryDto;
import com.example.repository.web.fo.InventoryEditFo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.web.fo.InventoryListFo;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class InventoryController {
    @Resource
    private InventoryService inventoryService;

    @GetMapping("/inventory/list")
    public ResponseResult<PageDto<InventoryDto>> getInventoryList(InventoryListFo inventoryListFo){
        Integer pageNum = inventoryListFo.getPageNum();
        Integer pageSize = inventoryListFo.getPageSize();
        Long typeid = inventoryListFo.getTypeid();
        String searchName = inventoryListFo.getSearchName();
        PageDto<InventoryDto> inventoryPage =inventoryService.getInventoryList(pageNum,pageSize,typeid,searchName);
        return new ResponseResult<PageDto<InventoryDto>>(200,"success",inventoryPage);
    }
    @PostMapping("/inventory/edit")
    public ResponseResult<Void> editInventory(@RequestBody InventoryEditFo inventoryEditFo){
        List<InventoryEditPo> editFoPoList = inventoryEditFo.getPoList();
        inventoryService.edit(editFoPoList);
        return new ResponseResult<Void>(200,"success",null);
    }

}
