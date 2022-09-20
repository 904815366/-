package com.woniu.repository;

import com.woniu.dao.ShipmentMapper;
import com.woniu.web.fo.AddShipmentFo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ShipmentRepository {

    @Resource
    private ShipmentMapper shipmentMapper;

//    private

//    新增出货订单
    public void addShipment(AddShipmentFo addShipmentFo){
        shipmentMapper.addShioment(addShipmentFo);
//        给资金发openfei

//        给仓库减库存

    }
}
