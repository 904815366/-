package com.example.repository.api.client;

import com.example.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("repository-service")
public interface RepositoryClient {
    @PostMapping("/goods/releasestock")
    ResponseResult<Void> releaseStock(@RequestParam("id") Long id,
                                            @RequestParam("num") Integer num);
}
