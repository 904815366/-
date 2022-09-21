package com.example.homeservice.web.converter;

import com.example.homeservice.dao.mysql.po.CustomersPo;
import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeservice.dao.redis.po.CustomersRedisPo;
import com.example.homeservice.dao.redis.po.SettlementAccountRedisPo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomersConverter {
    CustomersRedisPo from (CustomersPo po);
    CustomersPo from (CustomersRedisPo po);
}
