package com.woniu.service;

import com.woniu.dao.po.Shipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.web.fo.AddShipmentFo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
public interface ShipmentService extends IService<Shipment> {

    void insertShipment(AddShipmentFo addShipmentFo);

    //    修改出货状态
    void upShipmentStatus(Long id);

}
