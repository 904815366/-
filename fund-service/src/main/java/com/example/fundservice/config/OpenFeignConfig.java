package com.example.fundservice.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.example.homeserviceapi.http","com.example.shipment.api"})
public class OpenFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return  Logger.Level.FULL;
    }

}
