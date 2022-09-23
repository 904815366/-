package com.example.repository.api.client;

import com.example.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("repository-service")
public interface RepositoryClient {
    @PostMapping("/inventory/addship")
    ResponseResult<Void> addShip(@RequestParam("goodsid") Long goodsid,
                                 @RequestParam("num") Integer num,
                                 @RequestParam("id") String id,
                                 @RequestParam("time")String time);
}
