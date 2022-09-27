package com.woniuxy.purchase.dao.mysql;

import com.woniuxy.purchase.entity.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseReturnDaoTest {
    @Resource
    private PurchaseReturnDao purchaseReturnDao;
    @Resource
    private PurchaseDao purchaseDao;
    @Test
    void findAllPurchaseReturn() {
        List<DetailsByGoodsDto> byGoods = purchaseReturnDao.findReturnDetailsByGoods();
        for (DetailsByGoodsDto byGood : byGoods) {
            System.out.println(byGood);
        }
//        List<DetailsDto> aReturn = purchaseReturnDao.findReturn();
//        List<DetailsDto> details = purchaseDao.findDetails();
//        List<DetailsDto> detailsDtoList = Stream.of(aReturn, details).flatMap(Collection::stream).collect(Collectors.toList());
//        for (DetailsDto dto : detailsDtoList) {
//            System.out.println(dto);
//        }

//        for (PurchaseReturnList purchaseReturnList : purchaseReturnDao.findAllPurchaseReturn()) {
//            System.out.println(purchaseReturnList);
//        }
//        PurchaseReturnDto purchaseReturnById = purchaseReturnDao.findPurchaseReturnById(1L);
//        System.out.println(purchaseReturnById);
//        Integer integer = purchaseReturnDao.deleteById(1L);
//        System.out.println(integer);
//        Integer integer = purchaseReturnDao.modifyPaymentStatus(1L);
//        System.out.println(integer);
    }
}