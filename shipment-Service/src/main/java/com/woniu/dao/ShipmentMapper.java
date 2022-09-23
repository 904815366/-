package com.woniu.dao;

import com.woniu.dao.po.Shipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.repository.dto.ShipmentDto;
import com.woniu.web.fo.AddShipmentFo;
import com.woniu.web.fo.ShpimentFo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    //    修改出货状态
    void upShipmentStatus(@Param("id") Long id,@Param("status") String status);

    List<ShipmentDto> selShipments(ShpimentFo shpimentFo);

//    修改出货状态
    void upShipmentStatus(Long id);

    List<ShipmentDto> selShipments(ShpimentFo shpimentFo);

}
