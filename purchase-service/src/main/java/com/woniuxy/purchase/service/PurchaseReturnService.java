package com.woniuxy.purchase.service;

import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.dto.PurchaseReturnList;
import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.web.fo.PurchaseReturnListFo;

import java.util.List;

public interface PurchaseReturnService {
    PageInfo<PurchaseReturnList> getPurchaseRuturnList(PurchaseReturnListFo fo);

    PurchaseReturnDto findGetById(Long id);

    boolean deleteByIds(Long[] ids);

    void addReturn(PurchaseReturnPo po, List<PurchaseReturnDetailsPo> detailsPoList);

    boolean modifyReturn(PurchaseReturnPo po, List<PurchaseReturnDetailsPo> detailsPoList);
}
