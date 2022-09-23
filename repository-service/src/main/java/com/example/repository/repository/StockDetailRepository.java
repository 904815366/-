package com.example.repository.repository;

import com.example.repository.dao.mysql.StockDetailDao;
import com.example.repository.dao.mysql.po.StockDetailPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
@Slf4j
public class StockDetailRepository {
    @Resource
    private StockDetailDao stockDetailDao;
    public void addShip(String id, Integer num, Long goodsid, String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StockDetailPo detailPo = StockDetailPo.builder()
                .id(id)
                .goodsid(goodsid)
                .num(num)
                .type(1)
                .status(0)
                .time(LocalDateTime.parse(time,df))
                .build();
        stockDetailDao.save(detailPo);
    }
}
