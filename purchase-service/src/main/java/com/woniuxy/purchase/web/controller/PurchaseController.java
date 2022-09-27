package com.woniuxy.purchase.web.controller;

import com.example.util.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.annotation.IdempotentToken;
import com.woniuxy.purchase.dao.mysql.PurchaseDao;
import com.woniuxy.purchase.dao.mysql.PurchaseReturnDao;
import com.woniuxy.purchase.entity.dto.DetailsByGoodsDto;
import com.woniuxy.purchase.entity.dto.DetailsDto;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.dto.PurchaseList;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.repository.PurchaseRepository;
import com.woniuxy.purchase.service.PurchaseService;
import com.woniuxy.purchase.utils.SnowflakeIdGenerator;
import com.woniuxy.purchase.utils.UuidUtils;
import com.woniuxy.purchase.web.converter.PurchaseDetailFoConverter;
import com.woniuxy.purchase.web.converter.PurchaseFoConverter;
import com.woniuxy.purchase.web.converter.PurchaseFooConverter;
import com.woniuxy.purchase.web.fo.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;
    @Resource
    private PurchaseDao purchaseDao;
    @Resource
    private PurchaseReturnDao purchaseReturnDao;

    @PostMapping("/purchase/list")
    public ResponseResult<PageInfo<PurchaseList>> findPurchaseList(@Validated @RequestBody PurchaseListFo fo) {
        PageInfo<PurchaseList> purchaseList = purchaseService.getPurchaseList(fo);
        return new ResponseResult<>(200, "ok", purchaseList);
    }

    @GetMapping("/purchase/{purchaseId}")
    public ResponseResult<PurchaseDetail> findPuerchaseDetailByPurchaseId(@PathVariable Long purchaseId) {
        if (ObjectUtils.isEmpty(purchaseId)) {
            return new ResponseResult<>(400, "erro:参数有误!", null);
        } else {
            PurchaseDetail byPurchaseId = purchaseService.findByPurchaseId(purchaseId);
            return new ResponseResult<>(200, "ok", byPurchaseId);
        }
    }

    @PostMapping("/purchase/modify/status")
    @IdempotentToken
    public ResponseResult<String> modifyStatus(@Validated @RequestBody PurchaseModifyStatus modifyStatus) {
        String[] ids = modifyStatus.getIds().split(",");
        purchaseService.modifyStatus(ids, modifyStatus.getStatus(), modifyStatus.getValidToken());
        return new ResponseResult<>(200, "ok", "状态更新成功");
    }

    @PostMapping("/purchase/delete")
    @IdempotentToken
    public ResponseResult<String> deletePurchase(@Validated @RequestBody PurchaseDeleteById delete) {
        boolean reslut = purchaseService.deleteById(delete.getId(), delete.getValidToken());
        if (reslut) {
            return new ResponseResult<>(200, "ok", "删除成功");
        } else {
            return new ResponseResult<>(500, "erro", "删除id不存在");
        }

    }

    @PostMapping("/purchase/add")
    @IdempotentToken
    public ResponseResult<String> addPurchase(@Validated @RequestBody PurchaseAddFo fo) {
        PurchaseFoConverter converter = Mappers.getMapper(PurchaseFoConverter.class);
        PurchasePo purchasePo = converter.from(fo);
        //填充订单号
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator();
        String num = "CG-" + snowflakeIdGenerator.nextId();
        purchasePo.setInvoiceNumber(num);
        purchasePo.setVersion(1);
        purchasePo.setPaymentStatus(0);
        //填充订单生成时间
        purchasePo.setInvoiceTime(LocalDateTime.now());
        List<PurchaseDetailFo> foList = fo.getPurchaseDetailFoList();
        System.out.println(purchasePo);
        PurchaseDetailFoConverter converter2 = Mappers.getMapper(PurchaseDetailFoConverter.class);
        List<PurchaseDetailsPo> detailsPoList = converter2.from(foList);
        purchaseService.addPurchase(purchasePo, detailsPoList);
        return new ResponseResult<>(200, "ok", "新增成功!");
    }

    @PostMapping("/purchase/update")
    @IdempotentToken
    public ResponseResult<String> modifyPurchase(@Validated @RequestBody PurchaseModifyFo fo) {
        PurchaseFooConverter mapper = Mappers.getMapper(PurchaseFooConverter.class);
        PurchasePo purchasePo = mapper.from(fo);
        List<PurchaseDetailFo> purchaseDetailFoList = fo.getPurchaseDetailFoList();
        PurchaseDetailFoConverter converter2 = Mappers.getMapper(PurchaseDetailFoConverter.class);
        List<PurchaseDetailsPo> detailsPoList = converter2.from(purchaseDetailFoList);
        purchaseService.modifyById(purchasePo, detailsPoList);
        return new ResponseResult<>(200, "ok", "修改成功!");
    }

    @PostMapping("/purchase/modify/payment")
    public ResponseResult<String> modifyInPay(@RequestParam("id") Long id,@RequestParam("paymentStatus") Integer paymentStatus){
        boolean result = purchaseService.modifyPaymentStatus(id, paymentStatus);
        if(result){
            return new ResponseResult<>(200,"ok","修改成功!");
        }
        return new ResponseResult<>(500,"erro","id可能传错了哦!");
    }

    @GetMapping("/purchase/invoiceNumber/{id}")
    public ResponseResult<String> getInvoiceNumber(@PathVariable("id") Long id){
        String invoiceNumber = purchaseDao.findByPractical(id).getInvoiceNumber();
        return new ResponseResult<>(200,"ok",invoiceNumber);
    }

    @GetMapping("/purchase/details/list")
    public ResponseResult<List<DetailsDto>> getPurchase(){
        List<DetailsDto> aReturn = purchaseReturnDao.findReturn();
        List<DetailsDto> details = purchaseDao.findDetails();
        List<DetailsDto> detailsDtoList = Stream.of(aReturn, details).flatMap(Collection::stream).collect(Collectors.toList());
        return new ResponseResult<>(200,"ok",detailsDtoList);
    }

    @GetMapping("/purchase/details/goods/list")
    public ResponseResult<List<DetailsByGoodsDto>> getByGoodsDto(){
        List<DetailsByGoodsDto> detailsByGoods = purchaseReturnDao.findReturnDetailsByGoods();
        List<DetailsByGoodsDto> byGoods = purchaseDao.findDetailsByGoods();
        List<DetailsByGoodsDto> collect = Stream.of(detailsByGoods, byGoods).flatMap(Collection::stream).collect(Collectors.toList());
        return new ResponseResult<>(200,"ok",collect);
    }

}
