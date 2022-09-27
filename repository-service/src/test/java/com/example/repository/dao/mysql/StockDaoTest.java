package com.example.repository.dao.mysql;

import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.StockPo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockDaoTest {
@Resource
private StockDao stockDao;
    @Test
    void findByGoodsAndId() {
        StockPo stockPo = stockDao.findByGoods(GoodsPo.builder().id(1L).build());
        System.out.println(stockPo);
    }
}