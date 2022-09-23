package com.example.shipment.api;

import com.example.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shipment-Service")
public interface ShipmentClient {

    @PostMapping("/upShipmentStatus")
     ResponseResult<String> setShiStatus(@RequestParam("chdid") Long chdid);
}
