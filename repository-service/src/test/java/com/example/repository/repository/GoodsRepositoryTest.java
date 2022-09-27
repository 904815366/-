package com.example.repository.repository;

import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoodsRepositoryTest {
@Resource
    private GoodsService goodsService;
    @Test
    void e(){
        GoodsPo good = goodsService.findGood(1L);
        System.out.println(good);
    }
}