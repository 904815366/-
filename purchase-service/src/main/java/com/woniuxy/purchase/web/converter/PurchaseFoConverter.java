package com.woniuxy.purchase.web.converter;

import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.web.fo.PurchaseAddFo;
import org.mapstruct.Mapper;

@Mapper
public interface PurchaseFoConverter {
    PurchasePo from(PurchaseAddFo fo);
}
