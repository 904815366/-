package com.woniu.repository.converter;

import com.github.pagehelper.PageInfo;
import com.woniu.dao.po.Cusorder;
import com.woniu.repository.dto.CusorderDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CusorderConverter {

    List<CusorderDto> from(List<Cusorder> cusorders);

    CusorderDto from(Cusorder cusorder);

    PageInfo<CusorderDto> from(PageInfo<Cusorder> cusorderPageInfo);

}
