package com.woniu.repository;

import com.woniu.dao.CusorderMapper;
import com.woniu.dao.po.Cusorder;
import com.woniu.repository.converter.CusorderConverter;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.web.fo.CusorderFo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CusorderRepository {

    @Resource
    private CusorderMapper cusorderMapper;
    
    @Resource
    private CusorderConverter converter;

//    简单的查询方法
    public List<CusorderDto> getPageAllByfo(CusorderFo cusorderFo){
        List<Cusorder> po = cusorderMapper.querypageByfo(cusorderFo);
        List<CusorderDto> dtos = converter.from(po);
        return dtos;
    }

}
