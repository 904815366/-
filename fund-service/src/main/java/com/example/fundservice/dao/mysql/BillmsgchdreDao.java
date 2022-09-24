package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BillmsgchdreDao  extends BaseMapper<BillmsgchdrePo> {
    @Select("select * from billmsgchdre where reid = #{reid}")
    BillmsgchdrePo getThd(@Param("reid") Long reid);

    @Insert("insert into billmsgchdre values (#{reid},#{chdid},#{cstid},#{accid},#{account},#{status})")
    Integer addThd(BillmsgchdrePo billmsgchdrePo);
}
