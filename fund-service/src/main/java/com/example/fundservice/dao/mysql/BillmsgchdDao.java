package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BillmsgchdDao extends BaseMapper<BillmsgchdPo> {
    @Select("select * from billmsgchd where chdid = #{chdid}")
    BillmsgchdPo getChd(@Param("chdid") Long chdid);

    @Select("select * from billmsgchd where chdid = #{chdid} and status = '未审核'")
    BillmsgchdPo getChdByStatus(@Param("chdid") Long chdid);

    @Update("update billmsgchd set status='已通过' where chdid = #{chdid}")
    void delChd(@Param("chdid") Long chdid);

    @Insert("insert into billmsgchd values (#{chdid},#{cstid},#{accid},#{account},#{status})")
    Integer addChd(BillmsgchdPo billmsgchdPo);
}
