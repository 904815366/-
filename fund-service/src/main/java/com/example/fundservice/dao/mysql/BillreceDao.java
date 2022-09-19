package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillreceDao extends BaseMapper<BillrecePo> {
    @Select("select * from billrece")
    List<BillrecePo> list();
}
