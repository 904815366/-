package com.example.homeservice.web.converter;

import com.example.homeservice.dao.mysql.po.SupplierPo;
import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.dao.redis.po.SupplierRedisPo;
import com.example.homeservice.dao.redis.po.UsersRedisPo;
import com.example.homeservice.web.dto.UsersDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersConverter {
    UsersDto from (UsersPo usersPo);

    List<UsersDto> from (List<UsersPo> usersPos);

    UsersRedisPo fromRedisPo (UsersPo po);

    UsersPo from (UsersRedisPo po);

}
