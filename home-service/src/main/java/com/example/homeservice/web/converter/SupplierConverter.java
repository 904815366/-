package com.example.homeservice.web.converter;

<<<<<<< master
import com.example.homeservice.dao.mysql.po.CustomersPo;
import com.example.homeservice.dao.mysql.po.SupplierPo;
import com.example.homeservice.dao.redis.po.CustomersRedisPo;
=======
import com.example.homeservice.dao.mysql.po.SupplierPo;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
import com.example.homeservice.dao.redis.po.SupplierRedisPo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierConverter {
    SupplierRedisPo from (SupplierPo po);
    SupplierPo from (SupplierRedisPo po);
}
