package com.woniu.web;

import com.example.util.ResponseResult;
import com.woniu.anon.IdempotentToken;
import com.woniu.service.ReturnsalesService;
import com.woniu.service.ShipmentService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ReturnsalesController {

    @Resource
    private ReturnsalesService returnsalesService;

    @Resource
    private ShipmentService shipmentService;

//    新增退货单
    @IdempotentToken
    public ResponseResult<String> addReturnSales(){
        return null;
    }
}
