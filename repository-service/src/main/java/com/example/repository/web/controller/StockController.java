package com.example.repository.web.controller;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.service.StockDetailService;
import com.example.repository.service.StockService;
import com.example.repository.util.ResponseResult;
import com.example.repository.web.controller.dto.InventoryDto;
import com.example.repository.web.controller.dto.StockDto;
import com.example.repository.web.fo.InventoryListFo;
import com.example.repository.web.fo.StockListFo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StockController {
    @Resource
    private StockService stockService;

    @GetMapping("/stock/list")
    public ResponseResult<PageDto<StockDto>> getStockList(StockListFo stockListFo){
        Integer pageNum = stockListFo.getPageNum();
        Integer pageSize = stockListFo.getPageSize();
        Long typeid = stockListFo.getTypeid();
        String searchName = stockListFo.getSearchName();
        PageDto<StockDto> stockPage =stockService.getStockList(pageNum,pageSize,typeid,searchName);
        return new ResponseResult<PageDto<StockDto>>(200,"success",stockPage);
    }
}
