package com.example.homeservice.web.converter;

<<<<<<< master
import com.example.homeservice.dao.mysql.po.CustomersPo;
import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeservice.dao.redis.po.CustomersRedisPo;
import com.example.homeservice.dao.redis.po.SettlementAccountRedisPo;
=======
import com.example.homeservice.dao.redis.po.CustomersRedisPo;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomersConverter {
<<<<<<< master
    CustomersRedisPo from (CustomersPo po);
    CustomersPo from (CustomersRedisPo po);
=======
    CustomersRedisPo from (com.example.homeservice.dao.mysql.po.CustomersPo po);
    com.example.homeservice.dao.mysql.po.CustomersPo from (CustomersRedisPo po);
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
}
