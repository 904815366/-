package com.example.homeservice.dao.redis;


import com.example.homeservice.dao.redis.po.CustomersRedisPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomersRedis extends CrudRepository<CustomersRedisPo,Long> {
}
