package com.woniuxy.purchase.config;

import com.woniuxy.purchase.filter.HttpServletRequestReplacedFilter;
import com.woniuxy.purchase.interceptor.IdempotentTokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean httpServletRequestReplacedFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        // /* 是全部的请求拦截，和Interceptor的拦截地址/**区别开
        registration.addUrlPatterns("/*");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }

    @Resource
    private IdempotentTokenInterceptor idempotentTokenInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(idempotentTokenInterceptor).addPathPatterns("/**");
    }
}
