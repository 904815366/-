package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PurchaseDetailsDao extends BaseMapper<PurchaseDetailsPo> {
    List<PurchaseDetail> findPurchaseDetail(Integer purchaseId);

    void deleteByPurchaseId(Long purchaseId);

    void purchaseDetailsAdd(@Param("detailsPoList") List<PurchaseDetailsPo> detailsPoList);
}
