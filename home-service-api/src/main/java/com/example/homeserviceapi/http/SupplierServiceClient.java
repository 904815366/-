package com.example.homeserviceapi.http;

import com.example.homeserviceapi.http.fallback.CustomersServiceClientFallback;
import com.example.homeserviceapi.http.fallback.SupplierServiceClientFallback;
import com.example.homeserviceapi.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "home-service", fallback = SupplierServiceClientFallback.class)
public interface SupplierServiceClient {

    /**
     * 根据ID获取用户姓名   给资产微服务调用
     * @param id
     * @return
     */
    @GetMapping("/supplier/{id}")
    ResponseResult<String> queryNameById(@PathVariable("id") Long id);
}
