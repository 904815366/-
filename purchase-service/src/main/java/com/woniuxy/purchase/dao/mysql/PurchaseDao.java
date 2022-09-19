package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.dto.PurchaseList;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.web.fo.PurchaseListFo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PurchaseDao extends BaseMapper<PurchasePo> {
    List<PurchaseList> findPracticalList(PurchaseListFo fo);

    PurchaseDetail findPracticalById(Long purchaseId);

    void ModifyById(@Param("ids") String[] ids,@Param("status") Integer status);

}
