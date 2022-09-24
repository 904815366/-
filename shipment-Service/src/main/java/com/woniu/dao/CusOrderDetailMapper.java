package com.woniu.dao;

import com.woniu.dao.po.CusOrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Mapper
public interface CusOrderDetailMapper extends BaseMapper<CusOrderDetail> {

//    根据订单id删除订单详情
    void removeByOrderId(Long Oid);

//    根据订单id查询订单详情
    List<CusOrderDetail> selectByOrderId(Long Cusid);
}
