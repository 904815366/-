package com.woniu;

import com.woniu.dao.ReturnsalesMapper;
import com.woniu.po.Returnsales;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ShipmentServiceApplicationTests {

    @Autowired
    private ReturnsalesMapper returnsalesMapper;

    @Test
    void contextLoads() {
        Returnsales returnsales = returnsalesMapper.selectByid(1L);
        System.out.println(returnsales.getReturnMoney());
    }

}
