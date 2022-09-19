package com.woniuxy.purchase.repository;

import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseRepositoryTest {
    @Resource
    private PurchaseRepository purchaseRepository;
    @Test
    void findByPurchaseId() {
        PurchaseDetail byPurchaseId = purchaseRepository.findByPurchaseId(1L);
    }
}