package com.example.homeservice.dao.redis;


import com.example.homeservice.dao.redis.po.SettlementAccountRedisPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementAccountRedis extends CrudRepository<SettlementAccountRedisPo,Long> {
}
