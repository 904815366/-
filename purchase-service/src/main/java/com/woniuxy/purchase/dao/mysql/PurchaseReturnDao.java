package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface PurchaseReturnDao extends BaseMapper<PurchaseReturnPo> {
}
