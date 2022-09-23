package com.woniu.http;

import com.example.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public interface ShipmentClient {

    @PostMapping("/upShipmentStatus")
     ResponseResult<String> setShiStatus(Long id);
}
