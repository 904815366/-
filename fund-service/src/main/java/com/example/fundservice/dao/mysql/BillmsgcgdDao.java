package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;

@Mapper
public interface BillmsgcgdDao extends BaseMapper<BillmsgcgdPo> {
    @Select("select * from billmsgcgd where cgdid = #{cgdid}")
    BillmsgcgdPo getCgd(@Param("cgdid") Integer cgdid);

    @Select("select * from billmsgcgd where cgdid = #{cgdid} and status = '未审核'")
    BillmsgcgdPo getCgdByStatus(@Param("cgdid") Integer cgdid);

    @Update("update billmsgcgd set status='已通过' where cgdid = #{cgdid}")
    void delCgd(@Param("cgdid") Integer cgdid);

    @Insert("insert into billmsgcgd values (#{cgdid},#{gysid},#{accid},#{account},#{status})")
    Integer addCgd(BillmsgcgdPo billmsgcgdPo);
}
