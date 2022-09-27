package com.example.homeservice.web.converter;


import com.example.homeservice.dao.es.po.LeinftyLove;
import com.example.homeservice.dao.mysql.po.CustomersPo;
import com.example.homeservice.dao.redis.po.CustomersRedisPo;
import com.example.homeservice.web.dto.LeinftyLoveDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeinftyLoveConverter {
    LeinftyLoveDto from (LeinftyLove po);
}
