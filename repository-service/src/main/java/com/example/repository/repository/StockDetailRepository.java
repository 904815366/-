package com.example.repository.repository;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.StockDetailDao;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.dao.mysql.po.StockDetailPo;
import com.example.repository.web.controller.dto.InventoryDto;
import com.example.repository.web.controller.dto.StockDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class StockDetailRepository {
    @Resource
    private StockDetailDao stockDetailDao;
    public void addShip(String id, Integer num, Long goodsid, String time, GoodsPo goods,Integer type) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StockDetailPo detailPo = StockDetailPo.builder()
                .id(id)
                .goods(goods)
                .num(num)
                .type(type)
                .status(0)
                .time(LocalDateTime.parse(time,df))
                .build();
        stockDetailDao.save(detailPo);
    }

    public PageDto<StockDetailDto> findAll(Example<StockDetailPo> example, Pageable pageable) {
        Page<StockDetailPo> pagePo = stockDetailDao.findAll(example, pageable);
        PageDto<StockDetailDto> pageDto = new PageDto<>();
        List<StockDetailPo> content = pagePo.getContent();

        List<StockDetailDto> dtoList = new ArrayList<>();

        for (StockDetailPo po : content) {
            StockDetailDto stockDetailDto = StockDetailDto.builder()
                    .goodName(po.getGoods().getName())
                    .goodUnit(po.getGoods().getUnit())
                    .num(po.getNum())
                    .type(po.getType())
                    .id(po.getId())
                    .status(po.getStatus())
                    .time(po.getTime())
                    .build();
            dtoList.add(stockDetailDto);
        }
        PageDto<StockDetailDto> pageDtoList = pageDto.getPageDto(pagePo, dtoList);
        return pageDtoList;
    }
}
