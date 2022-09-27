package com.woniu.web;

import com.example.util.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.woniu.ControllerException.MyException;
import com.woniu.dao.po.Shipment;
import com.woniu.repository.ShipmentRepository;
import com.woniu.repository.dto.ShipmentDto;
import com.woniu.service.ShipmentService;
import com.woniu.web.fo.AddShipmentFo;
import com.woniu.web.fo.ShpimentFo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ShipmentController {

    @Resource
    private ShipmentService service;

    @Resource
    private ShipmentRepository shipmentRepository;

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

//    修改出货订单的状态查看是否收到款
    @PostMapping("/upShipmentStatus")
    public ResponseResult<String> setShiStatus(@RequestParam("chdid")Long id){
        try {
            Shipment byId = service.getById(id);
            if (ObjectUtils.isEmpty(byId)) {
                return new ResponseResult<>(500, "ERRO", "id不存在");
            }
            service.upShipmentStatus(id,"2");
            return new ResponseResult<>(200, "OK", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400, "ERRO", "修改失败");
        }
    }

//    查询出货列表
    @GetMapping("/getShipents")
    public ResponseResult<PageInfo<ShipmentDto>> getShipments(ShpimentFo shpimentFo){
        try {
            //        如果页码为空
            if (ObjectUtils.isEmpty(shpimentFo.getPageNum())) {
                shpimentFo.setPageNum(1);
            }
//        如果页大小为空
            if (ObjectUtils.isEmpty(shpimentFo.getPageSize())) {
                shpimentFo.setPageSize(5);
            }
            PageInfo<ShipmentDto> shipments  = shipmentRepository.getShipments(shpimentFo);
            return new ResponseResult<PageInfo<ShipmentDto>>(200, "OK", shipments);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<PageInfo<ShipmentDto>>(400, "ERRO", null);
        }

    }

}
