package com.example.homeserviceapi.http.fallback;


import com.example.homeserviceapi.http.CustomersServiceClient;
import com.example.homeserviceapi.http.SettlementServiceClient;
import com.example.homeserviceapi.utils.ResponseResult;
import org.springframework.stereotype.Component;

@Component
public class SettlementServiceClientFallback implements SettlementServiceClient {


    @Override
    public ResponseResult<String> queryAccountById(Long id) {
        System.out.println("queryAccountById() 方法的备胎方法执行了，意味着 openfeign http 请求失败。");
        return new ResponseResult<>(5000, "fallback return", "N/A");
    }
}
