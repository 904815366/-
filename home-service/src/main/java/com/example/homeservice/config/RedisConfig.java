package com.example.homeservice.config;

<<<<<<< master
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories(basePackages = "com.example.homeservice.dao.redis")
public class RedisConfig {
=======
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.example.homeservice.dao.redis")
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
//        config.setLockWatchdogTimeout();
        config.useSingleServer()
                .setAddress("redis://192.172.0.201:6379")
                .setKeepAlive(true);
        return Redisson.create(config);
    }
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
}
