package com.woniuxy.purchase.dao.redis;

import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseReturnRedisDao extends CrudRepository<PurchaseReturnDto,Long> {
}
