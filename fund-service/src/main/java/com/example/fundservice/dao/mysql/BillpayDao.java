package com.example.fundservice.dao.mysql;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillpayDao extends BaseMapper<BillpayPo> {

    @Select("select * from billpay")
    List<BillpayPo> list();

    @Insert("insert into billpay values (null,now(),#{cgdid},#{gysid},#{accid}," +
            "#{faccount},#{userid},#{fdecr},#{fstatus})")
    Integer add(BillpayPo billpayPo);

}
