package com.example.homeservice.config;

import com.example.homeservice.filter.IdempotentTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {
    @Resource
    private IdempotentTokenInterceptor idempotentTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idempotentTokenInterceptor);
    }
}
