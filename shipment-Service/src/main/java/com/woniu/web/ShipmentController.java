package com.woniu.web;

import com.example.util.ResponseResult;
import com.woniu.MyException;
import com.woniu.service.ShipmentService;
import com.woniu.web.fo.AddShipmentFo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ShipmentController {

    @Resource
    private ShipmentService service;

    @PostMapping("/addShipment")//新增出货单
    public ResponseResult<String> addShipment(@RequestBody AddShipmentFo addShipmentFo) {
        try {
            service.insertShipment(addShipmentFo);
            return new ResponseResult<>(200, "OK", "新增成功");
        } catch (Exception e) {
            if (e instanceof MyException){
                return new ResponseResult<>(500, "NO", "库存不足");
            }
            e.printStackTrace();
            return new ResponseResult<>(400, "ERRO", "新增失败");
        }
    }
}
