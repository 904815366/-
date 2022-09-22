package com.woniuxy.purchase.dao.mysql;

import com.woniuxy.purchase.entity.dto.ReturnGoods;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseReturnDetailsDaoTest {
    @Resource
    private PurchaseReturnDetailsDao purchaseReturnDetailsDao;
    @Test
    void findDetailsByReturnId() {
//        List<ReturnGoods> detailsByReturnId = purchaseReturnDetailsDao.findDetailsByReturnId(1L);
//        for (ReturnGoods returnGoods : detailsByReturnId) {
//            System.out.println(returnGoods);
//        }
        Long row = purchaseReturnDetailsDao.deleteByReturnId(1000L);
        System.out.println(row);
    }
}