package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.dto.ReturnGoods;
import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseReturnDetailsDao extends BaseMapper<PurchaseReturnDetailsPo> {
    List<ReturnGoods> findDetailsByReturnId(Long purchaseReturnId);

    Long deleteByReturnId(Long purchaseReturnId);
}
