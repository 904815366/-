package com.example.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.repository.dao.mysql")
@EnableDiscoveryClient
@SpringBootApplication
public class RepositoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepositoryServiceApplication.class, args);
    }

}
