package com.example.homeservice.web.converter;


import com.example.homeservice.dao.mysql.po.SupplierPo;
import com.example.homeservice.dao.redis.po.SupplierRedisPo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierConverter {
    SupplierRedisPo from (SupplierPo po);
    SupplierPo from (SupplierRedisPo po);
}
