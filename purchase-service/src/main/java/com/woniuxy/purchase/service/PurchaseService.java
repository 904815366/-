package com.woniuxy.purchase.service;

import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.dto.PurchaseList;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.web.fo.PurchaseListFo;

import java.util.List;

public interface PurchaseService {
    PageInfo<PurchaseList> getPurchaseList(PurchaseListFo fo);

    PurchaseDetail findByPurchaseId(Long purchaseId);

    void modifyStatus(String[] ids,Integer status,String validToken);

    boolean deleteById(Long id,String validToken);

    void addPurchase(PurchasePo po, List<PurchaseDetailsPo> detailsPoList);

    void modifyById(PurchasePo po, List<PurchaseDetailsPo> detailsPoList);
}
