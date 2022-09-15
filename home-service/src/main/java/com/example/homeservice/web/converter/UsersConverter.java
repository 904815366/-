package com.example.homeservice.web.converter;

import com.example.homeservice.dao.po.SignPo;
import com.example.homeservice.dao.po.UsersPo;
import com.example.homeservice.web.dto.SignsDto;
import com.example.homeservice.web.dto.UsersDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersConverter {
    UsersDto from (UsersPo usersPo);

    List<UsersDto> from (List<UsersPo> usersPos);


}
