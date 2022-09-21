package com.woniu.service.impl;

import com.woniu.dao.po.Shipment;
import com.woniu.dao.ShipmentMapper;
import com.woniu.repository.ShipmentRepository;
import com.woniu.service.ShipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.web.fo.AddShipmentFo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Service
public class ShipmentServiceImpl extends ServiceImpl<ShipmentMapper, Shipment> implements ShipmentService {

    @Resource
    private ShipmentRepository shipmentRepository;

    @Override
    public void insertShipment(AddShipmentFo addShipmentFo) {
        shipmentRepository.addShipment(addShipmentFo);
    }
}
