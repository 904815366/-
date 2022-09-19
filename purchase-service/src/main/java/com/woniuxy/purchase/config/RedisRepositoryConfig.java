package com.woniuxy.purchase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

@Configuration
@EnableRedisRepositories(basePackages = "com.woniuxy.purchase.dao.redis")
public class RedisRepositoryConfig {

}
