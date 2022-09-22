package com.woniuxy.purchase.web.converter;

import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import com.woniuxy.purchase.web.fo.ReturnDetailFo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReturnDetailFoConverter {
    PurchaseReturnDetailsPo from(ReturnDetailFo fo);

    List<PurchaseReturnDetailsPo> from(List<ReturnDetailFo> foList);
}
