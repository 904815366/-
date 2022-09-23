package com.example.repository;

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

    @Test
    void eeee(){
        String goods="[3-100, 33-100]";
        String substring = goods.substring(1, goods.length() - 1).replace(" ", "");
        System.out.println(substring);
        String[] goodStrings = substring.split(",");
        for (String goodString : goodStrings) {
            System.out.println(goodString);
        }
        System.out.println(goodStrings);
        for (String goodString : goodStrings) {
            String[] split = goodString
                    //.replace(" ", "")
                    .split("-");
            for (String s : split) {
                System.out.println(s);
            }
        }

    }

}
