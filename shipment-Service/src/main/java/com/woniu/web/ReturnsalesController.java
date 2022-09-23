package com.woniu.web;

import com.example.util.ResponseResult;
import com.woniu.anon.IdempotentToken;
import com.woniu.service.CusorderService;
import com.woniu.service.ReturnsalesService;
import com.woniu.service.ShipmentService;
import com.woniu.web.fo.ReturnsalesFo;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ReturnsalesController {

    @Resource
    private ReturnsalesService returnsalesService;

    @Resource
    private ShipmentService shipmentService;

    @Resource
    private CusorderService cusorderService;

//    新增退货单
    @IdempotentToken
    public ResponseResult<String> addReturnSales(ReturnsalesFo returnsalesFo){
//        新增退货单的要求

//        出货状态为不为2因为状态2属于发货中

//        ，情况   1只有一个商品，情况   2 多个商品，那就先查询再比对

//        通知库存，通知资金

//        修改原来的订单拆分，生成一个逻辑上的成功的订单和失败的订单


        return null;
    }
}