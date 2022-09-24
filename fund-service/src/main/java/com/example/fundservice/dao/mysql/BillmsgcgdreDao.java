package com.example.fundservice.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgcgdrePo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BillmsgcgdreDao  extends BaseMapper<BillmsgcgdrePo> {
    @Select("select * from billmsgcgdre where reid = #{reid}")
    BillmsgcgdrePo getThd(@Param("reid") Long reid);

    @Insert("insert into billmsgcgdre values (#{reid},#{cgdid},#{gysid},#{accid},#{account},#{status})")
    Integer addThd(BillmsgcgdrePo billmsgcgdrePo);
}
