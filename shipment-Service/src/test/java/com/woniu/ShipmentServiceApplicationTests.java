package com.woniu;

import com.example.fundserviceapi.client.FundClient;
import com.example.repository.api.client.RepositoryClient;
import com.woniu.dao.ShipmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class ShipmentServiceApplicationTests {

    @Resource//仓库的openfeign
    private RepositoryClient repositoryClient;

    @Resource
    private ShipmentMapper shipmentMapper;



    @Test
    void contextLoads() {
        shipmentMapper.upCusorderStatus(24L);
    }

    @Test
    void dom2() {
    }
    

}
