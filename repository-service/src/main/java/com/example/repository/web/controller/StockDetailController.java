package com.example.repository.web.controller;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.repository.StockDetailRepository;
import com.example.repository.service.StockDetailService;
import com.example.repository.util.ResponseResult;
import com.example.repository.web.controller.dto.StockDetailDto;
import com.example.repository.web.controller.dto.StockDto;
import com.example.repository.web.fo.StockDetailListFo;
import com.example.repository.web.fo.StockListFo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
public class StockDetailController {
    @Resource
    private StockDetailService stockDetailService;
    @GetMapping("/stockdetail/listbygoodsid")
    public ResponseResult<PageDto<StockDetailDto>> getStockList(StockDetailListFo stockDetailListFo){
        Integer pageNum = stockDetailListFo.getPageNum();
        Integer pageSize = stockDetailListFo.getPageSize();
        Long goodsid = stockDetailListFo.getGoodsid();
        Integer status = stockDetailListFo.getStatus();
        PageDto<StockDetailDto> stockDetailPage =stockDetailService.getStockDetailList(pageNum,pageSize,goodsid,status);
        return new ResponseResult<PageDto<StockDetailDto>>(200,"success",stockDetailPage);
    }
}
