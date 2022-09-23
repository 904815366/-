package com.woniuxy.purchase.web.controller;

import com.example.util.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.annotation.IdempotentToken;
import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.dto.PurchaseReturnList;
import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.service.PurchaseReturnService;
import com.woniuxy.purchase.utils.SnowflakeIdGenerator;
import com.woniuxy.purchase.web.converter.PurchaseReturnFoConverter;
import com.woniuxy.purchase.web.converter.ReturnDetailFoConverter;
import com.woniuxy.purchase.web.converter.ReturnFoConverter;
import com.woniuxy.purchase.web.fo.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PurchaseReturnController {
    @Resource
    private PurchaseReturnService purchaseReturnService;

    @PostMapping("/return/list")
    public ResponseResult<PageInfo<PurchaseReturnList>> findPurchaseReturnList(@Validated @RequestBody PurchaseReturnListFo fo) {
        PageInfo<PurchaseReturnList> pageInfo = purchaseReturnService.getPurchaseRuturnList(fo);
        return new ResponseResult<>(200, "ok", pageInfo);
    }

    @GetMapping("/return/{id}")
    public ResponseResult<PurchaseReturnDto> findPurchaseReturnById(@PathVariable Long id){
        System.out.println(id);
        if (ObjectUtils.isEmpty(id)) {
            return new ResponseResult<>(400,"参数有误!",null);
        }else {
            PurchaseReturnDto dto = purchaseReturnService.findGetById(id);
            return new ResponseResult<>(200,"ok",dto);
        }
    }
    @PostMapping("/return/delete")
    @IdempotentToken
    public ResponseResult<String> deleteByIds(@Validated @RequestBody PurchaseReturnDeleteByIds ids){
        boolean result = purchaseReturnService.deleteByIds(ids.getIds());
        if(result){
            return new ResponseResult<>(200,"ok","删除成功!");
        }else {
            return new ResponseResult<>(400,"ok","id有误!");
        }
    }

    @PostMapping("/return/add")
    @IdempotentToken
    public ResponseResult<String> addReturn(@Validated @RequestBody PurchaseReturnAddFo fo){
        PurchaseReturnFoConverter mapper = Mappers.getMapper(PurchaseReturnFoConverter.class);
        PurchaseReturnPo po = mapper.form(fo);
        po.setActive(1);
        po.setStatus(0);
        po.setVersion(1);
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();
        String num="CGTH-"+idGenerator.nextId();
        po.setInvoiceNumber(num);
        po.setInvoiceTime(LocalDateTime.now());
        List<ReturnDetailFo> returnDetailFoList = fo.getReturnDetailFoList();
        ReturnDetailFoConverter converter = Mappers.getMapper(ReturnDetailFoConverter.class);
        List<PurchaseReturnDetailsPo> detailsPos = converter.from(returnDetailFoList);
        purchaseReturnService.addReturn(po,detailsPos);
        return new ResponseResult<>(200,"ok","添加成功!");
    }

    @PostMapping("/return/modify")
    public ResponseResult<String> updateReturn(@Validated @RequestBody PurchaseReturnModifyFo fo){
        ReturnFoConverter mapper = Mappers.getMapper(ReturnFoConverter.class);
        PurchaseReturnPo purchaseReturnPo = mapper.from(fo);
        List<ReturnDetailFo> returnDetailFoList = fo.getReturnDetailFoList();
        ReturnDetailFoConverter converter = Mappers.getMapper(ReturnDetailFoConverter.class);
        List<PurchaseReturnDetailsPo> detailsPos = converter.from(returnDetailFoList);
        boolean result = purchaseReturnService.modifyReturn(purchaseReturnPo, detailsPos);
        if(result){
            return new ResponseResult<>(200,"ok","修改成功!");
        }else {
            return new ResponseResult<>(400,"erro","参数不对!");
        }
    }

    public ResponseResult<String> modifySatuas(){
        return null;
    }

}
