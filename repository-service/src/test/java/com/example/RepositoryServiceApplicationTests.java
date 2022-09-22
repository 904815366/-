package com.example;

import com.example.repository.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class RepositoryServiceApplicationTests {
    @Resource
    private GoodsService goodsService;

    @Test
    void contextLoads() {
        goodsService.releaseStock(1L, 10);
    }

}
