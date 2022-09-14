package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * spring-security 认证：
 *
 * step: 1. 实现 UserDetailsService，负责提供用户名密码权限的“标准答案”。以供 UsernamePasswordFilter 做比对工作。
 *
 * step: 2. 通过配置，使用我们自定义的 UserDetailsService （在配置的过程中，会涉及到配置一个密码加密器）。
 *          configure(AuthenticationManagerBuilder auth)
 *
 * step: 3. 定义登录、失败之后返回给前端的 JSON 串，需要去实现 SuccessHandler 和 FailureHandler 。
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.auth.mysql")
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }



}
