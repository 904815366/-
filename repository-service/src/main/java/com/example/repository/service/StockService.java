package com.example.repository.service;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.GoodsTypePo;
import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.dao.mysql.po.StockPo;
import com.example.repository.repository.InventoryRepository;
import com.example.repository.repository.StockRepository;
import com.example.repository.web.controller.dto.InventoryDto;
import com.example.repository.web.controller.dto.StockDto;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
@GlobalTransactional
public class StockService {
    @Resource
    private StockRepository stockRepository;
    @Resource
    private InventoryRepository inventoryRepository;
    @Resource
    private GoodsTypeService goodsTypeService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private StockDetailService stockDetailService;
    public PageDto<StockDto> getStockList(Integer pageNum, Integer pageSize, Long typeid, String searchName) {
        GoodsTypePo type = new GoodsTypePo();
        if (!"".equals(typeid)&& typeid!=null){
            type = goodsTypeService.getTypeById(typeid);
        }
        searchName="".equals(searchName) ? null:searchName;
        Pageable pageable= PageRequest.of(pageNum, pageSize);
        GoodsPo goodsPo=GoodsPo.builder().goodsType(type).name(searchName).build();
        StockPo stockPo=StockPo.builder().goods(goodsPo).build();
        Example<StockPo> example=Example.of(stockPo);
        PageDto<StockDto> pageDto=stockRepository.findAll(example,pageable);
        return pageDto;
    }
}
