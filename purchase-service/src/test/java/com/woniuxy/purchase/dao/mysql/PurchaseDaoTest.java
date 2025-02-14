package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.purchase.entity.dto.*;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.web.fo.PurchaseListFo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class PurchaseDaoTest {
    @Resource
    private PurchaseDao purchaseDao;
    @Test
    void demo() throws JsonProcessingException {
        List<DetailsByGoodsDto> detailsByGoods = purchaseDao.findDetailsByGoods();
        for (DetailsByGoodsDto byGood : detailsByGoods) {
            System.out.println(byGood);
        }
//        List<DetailsDto> details = purchaseDao.findDetails();
//        for (DetailsDto detail : details) {
//            System.out.println(detail);
//        }
//        GetInvoiceNumber byPractical = purchaseDao.findByPractical(7L);
//        System.out.println(byPractical.getInvoiceNumber());
//        PurchaseDetail practicalById = purchaseDao.findPracticalById(8L);
//        String invoiceNumber = practicalById.getInvoiceNumber();
//        String invoiceTime = practicalById.getInvoiceTime();
//        List<String> collect = practicalById.getGoods().stream().filter(goods -> goods.getId() != null && goods.getNum() != null)
//                .map(goods -> String.format("%s-%s", goods.getId(), goods.getNum()))
//                .collect(Collectors.toList());
//        String json="{\"invoiceNumber\":\""+invoiceNumber+"\",\"invoiceTime\":\""+invoiceTime+"\",\"goods\":\""+collect+"\"}";
//        System.out.println(json);
    }
}