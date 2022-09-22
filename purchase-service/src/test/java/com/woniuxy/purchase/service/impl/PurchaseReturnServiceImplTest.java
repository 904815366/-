package com.woniuxy.purchase.service.impl;

import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.service.PurchaseReturnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseReturnServiceImplTest {
    @Resource
    private PurchaseReturnService purchaseReturnService;
    @Test
    void findGetById() {
        PurchaseReturnDto getById = purchaseReturnService.findGetById(1L);
        System.out.println(getById);
    }
}