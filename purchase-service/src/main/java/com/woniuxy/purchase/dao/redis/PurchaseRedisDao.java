package com.woniuxy.purchase.dao.redis;

import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRedisDao extends CrudRepository<PurchaseDetail,Long> {
}
