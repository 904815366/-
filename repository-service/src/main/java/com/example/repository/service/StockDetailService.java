package com.example.repository.service;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.dao.mysql.po.StockDetailPo;
import com.example.repository.repository.StockDetailRepository;
import com.example.repository.web.controller.dto.InventoryDto;
import com.example.repository.web.controller.dto.StockDetailDto;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@GlobalTransactional
public class StockDetailService {
    @Resource
    private StockDetailRepository stockDetailRepository;
    @Resource
    private GoodsService goodsService;

    public void addShip(String id, Integer num, Long goodsid, String time,GoodsPo goods,Integer type) {
        stockDetailRepository.addShip(id,num,goodsid,time,goods,type);
    }

    public PageDto<StockDetailDto> getStockDetailList(Integer pageNum,
                                                      Integer pageSize,
                                                      Long goodsid,
                                                      Integer status) {
        GoodsPo goodsPo = new GoodsPo();
        if (!"".equals(goodsid)&& goodsid!=null){
            goodsPo = goodsService.findGood(goodsid);
            status="".equals(status) ? null:status;
            Pageable pageable= PageRequest.of(pageNum, pageSize);
            StockDetailPo stockDetailPo=StockDetailPo.builder()
                .goods(goodsPo)
                .status(status)
                .build();
            Example<StockDetailPo> example=Example.of(stockDetailPo);
            PageDto<StockDetailDto> pageDto=stockDetailRepository.findAll(example,pageable);
            return pageDto;
        }
        return null;
    }
}
