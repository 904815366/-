package com.woniu.service;

import com.woniu.dao.po.CusOrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
public interface CusOrderDetailService extends IService<CusOrderDetail> {

    void deltODetailByOid(Long oid);

    List<CusOrderDetail> selectByOrderId(Long Cusid);
}
