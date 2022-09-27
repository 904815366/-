package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgcgdrePo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BillmsgcgdreDao  extends BaseMapper<BillmsgcgdrePo> {
    @Select("select * from billmsgcgdre where cgdid = #{cgdid}")
    BillmsgcgdrePo getThd(@Param("cgdid") Long cgdid);

    @Insert("insert into billmsgcgdre values (#{reid},#{cgdid},#{gysid},#{accid},#{account},#{status})")
    Integer addThd(BillmsgcgdrePo billmsgcgdrePo);

    @Select("select * from billmsgcgdre where cgdid = #{cgdid} and status='未审核'")
    BillmsgcgdrePo getThdByStatus(@Param("cgdid")Long cgdid);

    @Update("update billmsgcgdre set status='已通过' where cgdid=#{cgdid}")
    void delThd(@Param("cgdid")Long cgdid);

    @Select("select reid from billmsgcgdre where cgdid = #{cgdid}")
    Long getReid(@Param("cgdid")Long cgdid);
}
