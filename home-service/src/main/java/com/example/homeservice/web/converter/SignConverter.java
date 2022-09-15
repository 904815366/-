package com.example.homeservice.web.converter;

import com.example.homeservice.dao.po.SignPo;
import com.example.homeservice.web.dto.SignsDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SignConverter {
    SignsDto from (SignPo signPo);

    List<SignsDto> from (List<SignPo> signPo);


}
