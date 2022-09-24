package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillreceDao extends BaseMapper<BillrecePo> {
    @Select("select * from billrece where sstatus = '正常' and saccount > 0")
    List<BillrecePo> billreceList();

    @Insert("insert into billrece values (null,now(),'暂未修改',#{chdid},#{cstid},#{accid}," +
            "#{saccount},#{userid},#{sdecr},#{sstatus})")
    Integer addBillrece(BillrecePo billrecePo);

    @Update("update billrece set sstatus='销毁' where sno=#{sno}")
    Integer delBillrece(@Param("sno")Long sno);

    @Select("select * from billrece where sno=#{sno} and sstatus='正常' and saccount > 0")
    BillrecePo getBillreceByStatus(@Param("sno") Long sno);
}
