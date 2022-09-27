package com.example.repository.repository;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.StockDao;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.dao.mysql.po.StockPo;
import com.example.repository.web.controller.dto.InventoryDto;
import com.example.repository.web.controller.dto.StockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Repository
@Slf4j
public class StockRepository {
    @Resource
    private StockDao stockDao;
    public PageDto<StockDto> findAll(Example<StockPo> example, Pageable pageable) {
        Page<StockPo> pagePo = stockDao.findAll(example, pageable);
        PageDto<StockDto> pageDto = new PageDto<>();
        List<StockPo> content = pagePo.getContent();

        List<StockDto> dtoList = new ArrayList<>();

        for (StockPo po : content) {
            StockDto stockDto = StockDto.builder()
                    .goodsid(po.getGoods().getId())
                    .goodsName(po.getGoods().getName())
                    .size(po.getGoods().getSize())
                    .id(po.getId())
                    .stock(po.getGoods().getStock())
                    .typeName(po.getGoods().getGoodsType().getName())
                    .unit(po.getGoods().getUnit())
                    .innum(po.getInnum())
                    .outnum(po.getOutnum())
                    .build();
            dtoList.add(stockDto);
        }
        PageDto<StockDto> pageDtoList = pageDto.getPageDto(pagePo, dtoList);
        return pageDtoList;
    }



    public void addInventory(Long goodsId, Integer innum,GoodsPo good) {
        StockPo stockPo = stockDao.findByGoods(good);
        stockPo.setInnum(stockPo.getInnum()+innum);
        stockDao.save(stockPo);
    }

    public void addOutnum(Long goodsId, Integer outnum,GoodsPo good) {
        StockPo stockPo = stockDao.findByGoods(good);
        System.out.println(stockPo);
        stockPo.setOutnum(stockPo.getOutnum()+outnum);
        stockDao.save(stockPo);
    }
}
