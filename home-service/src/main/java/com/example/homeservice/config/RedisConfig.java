package com.example.homeservice.config;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories(basePackages = "com.example.homeservice.dao.redis")
public class RedisConfig {
}
