package com.woniuxy.purchase.web.converter;

import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.web.fo.PurchaseDetailFo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PurchaseDetailFoConverter {
    PurchaseDetailsPo from(PurchaseDetailFo fo);

    List<PurchaseDetailsPo> from(List<PurchaseDetailFo> foList);
}
