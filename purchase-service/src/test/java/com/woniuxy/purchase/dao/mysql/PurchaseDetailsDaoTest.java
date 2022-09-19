package com.woniuxy.purchase.dao.mysql;

import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseDetailsDaoTest {
    @Resource
    private PurchaseDetailsDao purchaseDetailsDao;
    @Test
    void findPurchaseDetail() {
        for (PurchaseDetail detail : purchaseDetailsDao.findPurchaseDetail(1)) {
            System.out.println(detail);
        }
    }
}