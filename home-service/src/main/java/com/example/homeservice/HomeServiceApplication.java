package com.example.homeservice;

import com.example.homeservice.utils.MinioUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.homeservice.dao.mysql")
@EnableElasticsearchRepositories(basePackages = "com.example.homeservice.dao.es.po")
public class HomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeServiceApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }


    @Bean
    public MinioUtils minioUtils() {
        return new MinioUtils("http://192.172.0.201:9090", "minioadmin", "minioadmin");
    }
}
