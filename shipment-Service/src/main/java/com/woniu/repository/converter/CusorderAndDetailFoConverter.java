package com.woniu.repository.converter;

import com.woniu.dao.po.Cusorder;
import com.woniu.web.fo.AddCusAndDetailFo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface
CusorderAndDetailFoConverter {
    Cusorder from(AddCusAndDetailFo cusAndDetailFo);
}
