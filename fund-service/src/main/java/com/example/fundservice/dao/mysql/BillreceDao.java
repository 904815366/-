package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillreceDao extends BaseMapper<BillrecePo> {
    @Select("select * from billrece")
    List<BillrecePo> list();



    @Insert("insert into billrece values (null,now(),#{chdid},#{cstid},#{accid}," +
            "#{saccount},#{userid},#{sdecr},#{sstatus})")
    Integer add(BillrecePo billrecePo);
}
