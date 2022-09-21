package com.woniu.repository.converter;

import com.woniu.dao.po.Shipment;
import com.woniu.web.fo.AddShipmentFo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentConverter {

    Shipment from(AddShipmentFo addShipmentFo);
}
