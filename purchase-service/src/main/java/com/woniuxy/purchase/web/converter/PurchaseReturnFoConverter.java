package com.woniuxy.purchase.web.converter;

import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.web.fo.PurchaseReturnAddFo;
import org.mapstruct.Mapper;

@Mapper
public interface PurchaseReturnFoConverter {
    PurchaseReturnPo form(PurchaseReturnAddFo fo);
}
