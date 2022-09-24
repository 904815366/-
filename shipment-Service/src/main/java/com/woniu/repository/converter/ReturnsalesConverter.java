package com.woniu.repository.converter;

import com.woniu.dao.po.Returnsales;
import com.woniu.web.fo.ReturnsalesFo;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ReturnsalesConverter {
    Returnsales from(ReturnsalesFo returnsalesFo);
}
