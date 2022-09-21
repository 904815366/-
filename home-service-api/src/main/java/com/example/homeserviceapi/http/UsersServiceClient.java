package com.example.homeserviceapi.http;

import com.example.homeserviceapi.http.fallback.UsersServiceClientFallback;
import com.example.homeserviceapi.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "home-service", fallback = UsersServiceClientFallback.class)
public interface UsersServiceClient {

    /**
     * 根据ID获取用户姓名   给资产微服务调用
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    ResponseResult<String> queryNameById(@PathVariable("id") Long id);
}
