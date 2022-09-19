package com.woniuxy.purchase.web.controller;

import com.example.util.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.dto.PurchaseList;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.service.PurchaseService;
import com.woniuxy.purchase.web.converter.PurchaseDetailFoConverter;
import com.woniuxy.purchase.web.converter.PurchaseFoConverter;
import com.woniuxy.purchase.web.fo.*;
import org.mapstruct.factory.Mappers;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.jca.cci.CciOperationNotSupportedException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    @PostMapping("/purchase/list")
    public ResponseResult<PageInfo<PurchaseList>> findPurchaseList(@Validated @RequestBody PurchaseListFo fo) {
        PageInfo<PurchaseList> purchaseList = purchaseService.getPurchaseList(fo);
        return new ResponseResult<>(200, "ok", purchaseList);
    }

    @GetMapping("/purchase/{purchaseId}")
    public ResponseResult<PurchaseDetail> findPuerchaseDetailByPurchaseId(@PathVariable Long purchaseId){
        if (ObjectUtils.isEmpty(purchaseId)) {
            return new ResponseResult<>(400,"erro:参数有误!",null);
        }else {
            PurchaseDetail byPurchaseId = purchaseService.findByPurchaseId(purchaseId);
            return new ResponseResult<>(200,"ok",byPurchaseId);
        }
    }

    @PostMapping("/purchase/modify/status")
    public ResponseResult<String> modifyStatus(@Validated @RequestBody PurchaseModifyStatus modifyStatus){
        String[] ids = modifyStatus.getIds().split(",");
        boolean  result = purchaseService.modifyStatus(ids , modifyStatus.getStatus(), modifyStatus.getValidToken());
       if(result){
           return new ResponseResult<>(200,"ok","状态更新成功");
       }else {
           return new ResponseResult<>(500,"erro","状态更新失败");
       }
    }

    @PostMapping("/purchase/delete")
    public ResponseResult<String> deletePurchase(@Validated @RequestBody PurchaseDeleteById delete){
        boolean result = purchaseService.deleteById(delete.getId(), delete.getValidToken());
        if(result){
            return new ResponseResult<>(200,"ok","删除成功");
        }else {
            return new ResponseResult<>(500,"erro","删除失败");
        }
    }

    @PostMapping("/purchase/add")
    public ResponseResult<String> addPurchase(@Validated @RequestBody PurchaseAddFo fo){
        System.out.println(fo);
        PurchaseFoConverter converter = Mappers.getMapper(PurchaseFoConverter.class);
        PurchasePo purchasePo = converter.from(fo);
        purchasePo.setInvoiceTime(LocalDateTime.now());
        List<PurchaseDetailFo> foList = fo.getPurchaseDetailFoList();
        System.out.println(purchasePo);
        PurchaseDetailFoConverter converter2 = Mappers.getMapper(PurchaseDetailFoConverter.class);
        List<PurchaseDetailsPo> detailsPoList = converter2.from(foList);
        purchaseService.addPurchase(purchasePo,detailsPoList);
        return new ResponseResult<>(200,"ok","新增成功!");
    }

}
