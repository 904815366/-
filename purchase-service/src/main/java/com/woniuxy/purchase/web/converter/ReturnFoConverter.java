package com.woniuxy.purchase.web.converter;

import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.web.fo.PurchaseReturnModifyFo;
import org.mapstruct.Mapper;

@Mapper
public interface ReturnFoConverter {
    PurchaseReturnPo from(PurchaseReturnModifyFo fo);
}
