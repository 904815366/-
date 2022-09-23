package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BillmsgcgdDao extends BaseMapper<BillmsgcgdPo> {
    @Select("select * from billmsgcgd where cgdid = #{cgdid}")
    BillmsgcgdPo getCgd(@Param("cgdid") Long cgdid);

    @Select("select * from billmsgcgd where cgdid = #{cgdid} and status = '未审核'")
    BillmsgcgdPo getCgdByStatus(@Param("cgdid") Long cgdid);

    @Update("update billmsgcgd set status='已通过' where cgdid = #{cgdid}")
    void delCgd(@Param("cgdid") Long cgdid);

    @Insert("insert into billmsgcgd values (#{cgdid},#{gysid},#{accid},#{account},#{status},#{type})")
    Integer addCgd(BillmsgcgdPo billmsgcgdPo);

    @Select("select gysid from billmsgcgd where cgdid=#{cgdid}")
    Long getGysid(@Param("cgdid")Long cgdid);
}
