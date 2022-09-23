package com.woniuxy.purchaseserviceapi.client;

import com.example.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("purchase-service")
public interface MessageClient {
    @PostMapping("/message/modify")
    public ResponseResult<String> modifyStatus(@RequestParam("id") Long id,@RequestParam("status") String status);
}
