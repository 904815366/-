package com.example.homeservice.dao.redis;

import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.dao.redis.po.UsersRedisPo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRedis extends CrudRepository<UsersRedisPo,Long> {
}
