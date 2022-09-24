package com.woniu.repository;

import com.woniu.dao.CusOrderDetailMapper;
import com.woniu.dao.po.CusOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CusorderDetailRepository {

    @Autowired
    private CusOrderDetailMapper cusOrderDetailMapper;

//    新增客户订单详情办法
    public void AddCusDetail(CusOrderDetail cusOrderDetail){
        cusOrderDetailMapper.insert(cusOrderDetail);
    }

//    简单的根据订单id删除订单详情方法
    public void deltODetailByOid(Long oid){
        cusOrderDetailMapper.removeByOrderId(oid);
    }

//    简单的根据订单id查询订单详情
    public List<CusOrderDetail> selectByCusID(Long CusOid){
        List<CusOrderDetail> cusOrderDetails = cusOrderDetailMapper.selectByOrderId(CusOid);
        return cusOrderDetails;
    }
}
