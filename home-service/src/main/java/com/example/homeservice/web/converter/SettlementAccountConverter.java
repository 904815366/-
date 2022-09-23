package com.example.homeservice.web.converter;

import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeservice.dao.mysql.po.SignPo;
import com.example.homeservice.dao.redis.po.SettlementAccountRedisPo;
import com.example.homeservice.web.dto.SignsDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SettlementAccountConverter {
    SettlementAccountRedisPo from (SettlementAccountPo po);
    SettlementAccountPo from (SettlementAccountRedisPo po);
}
