package com.woniuxy.purchase.service.impl;

import com.woniuxy.purchase.service.PurchaseService;
import com.woniuxy.purchase.web.fo.PurchaseListFo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseServiceImplTest {
    @Resource
    private PurchaseService purchaseService;
    @Test
    void getPurchaseList() {
        PurchaseListFo fo = new PurchaseListFo();
        fo.setPageNum(2);
        fo.setPageSzie(5);
        purchaseService.getPurchaseList(fo);
    }
}