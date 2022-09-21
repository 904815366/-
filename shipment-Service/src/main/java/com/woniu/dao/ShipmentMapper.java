package com.woniu.dao;

import com.woniu.dao.po.Shipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.web.fo.AddShipmentFo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Mapper
public interface ShipmentMapper extends BaseMapper<Shipment> {

//    修改订单状态
    void upCusorderStatus(Long cid);

//    void addShioment(Shipment shipment);

}
