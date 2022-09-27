package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BillmsgchdreDao  extends BaseMapper<BillmsgchdrePo> {
    @Select("select * from billmsgchdre where chdid = #{chdid}")
    BillmsgchdrePo getThd(@Param("chdid") Long chdid);

    @Insert("insert into billmsgchdre values (#{reid},#{chdid},#{cstid},#{accid},#{account},#{status})")
    Integer addThd(BillmsgchdrePo billmsgchdrePo);

    @Select("select * from billmsgchdre where chdid = #{chdid} and status='未审核'")
    BillmsgchdrePo getThdByStatus(@Param("chdid") Long chdid);

    @Update("update billmsgchdre set status='已通过' where chdid=#{chdid}")
    void delThd(@Param("chdid")Long chdid);

    @Select("select reid from billmsgchdre where chdid = #{chdid}")
    Long getReid(@Param("chdid")Long chdid);
}
