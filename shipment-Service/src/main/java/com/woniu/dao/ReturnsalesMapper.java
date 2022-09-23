package com.woniu.dao;

import com.woniu.dao.po.CusOrderDetail;
import com.woniu.dao.po.Returnsales;
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
public interface ReturnsalesMapper extends BaseMapper<Returnsales> {

//    根据出货Id查询订单详情
    List<CusOrderDetail> selectCusOrDetail();


}
