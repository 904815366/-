package com.woniu.repository;

import com.woniu.dao.ReturnsalesMapper;
import com.woniu.repository.dto.ReturnsalesDto;
import com.woniu.web.fo.PageReturnSalesFo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ReturnsalesRepository {

    @Resource
    private ReturnsalesMapper returnsalesMapper;

//    带条件分页查询
    public List<ReturnsalesDto> getPageReturnSales(PageReturnSalesFo pageReturnSalesFo){
        List<ReturnsalesDto> pageRetuensaless = returnsalesMapper.getPageRetuensaless(pageReturnSalesFo);
        return pageRetuensaless;
    }
}
