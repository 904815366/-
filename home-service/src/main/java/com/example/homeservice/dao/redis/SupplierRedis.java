package com.example.homeservice.dao.redis;

import com.example.homeservice.dao.redis.po.SupplierRedisPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRedis extends CrudRepository<SupplierRedisPo,Long> {
}
