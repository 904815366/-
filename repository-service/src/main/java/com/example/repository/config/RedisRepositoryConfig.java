package com.example.repository.config;

import io.lettuce.core.ReadFrom;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.example.repository.dao.redis")
public class RedisRepositoryConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
//        config.setLockWatchdogTimeout();
        config.useSingleServer()
                .setAddress("redis://192.172.0.201:6379")
                .setKeepAlive(true);
        return Redisson.create(config);
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer configurationBuilderCustomizer() {
        return builder -> builder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}
