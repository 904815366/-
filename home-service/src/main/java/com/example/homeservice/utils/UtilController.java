package com.example.homeservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UtilController {
    @Autowired
    private StringRedisTemplate template;


    /**
     * 生成UUID,存入redis中,uuid返回给页面
     * @return
     */
    @RequestMapping("/getIdempotentToken")
    public String getuuidToken(){
        String uuid = UUID.randomUUID().toString();
        template.opsForValue().set(uuid,uuid);
        return uuid;
    }
}
