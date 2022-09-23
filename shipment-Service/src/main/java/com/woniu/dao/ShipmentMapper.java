package com.woniu.dao;

import com.woniu.dao.po.Shipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.repository.dto.ShipmentDto;
import com.woniu.web.fo.AddShipmentFo;
import com.woniu.web.fo.ShpimentFo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

//    修改出货状态
    void upShipmentStatus(Long id);

    List<ShipmentDto> selShipments(ShpimentFo shpimentFo);

}
