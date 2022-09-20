package com.woniuxy.purchase.web.converter;

import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.web.fo.PurchaseModifyFo;
import org.mapstruct.Mapper;

@Mapper
public interface PurchaseFooConverter {
    PurchasePo from(PurchaseModifyFo fo);
}
