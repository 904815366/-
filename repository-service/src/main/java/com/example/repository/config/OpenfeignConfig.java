package com.example.repository.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.woniuxy.purchaseserviceapi.client")
public class OpenfeignConfig {
}
