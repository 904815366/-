package com.example.repository.web.controller;

import com.example.repository.service.GoodsService;
import com.example.repository.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @PostMapping("/goods/releasestock")
    public ResponseResult<Void> releaseStock(@RequestParam("id") Long id,
                                             @RequestParam("num") Integer num){
        goodsService.releaseStock(id,num);
        return new ResponseResult<>(200,"success",null);
    }
}
