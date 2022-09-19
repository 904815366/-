package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.dto.PurchaseList;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.web.fo.PurchaseListFo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class PurchaseDaoTest {
    @Resource
    private PurchaseDao purchaseDao;
    @Test
    void demo(){
        PurchaseDetail practicalById = purchaseDao.findPracticalById(1L);
        System.out.println(practicalById);
    }
}