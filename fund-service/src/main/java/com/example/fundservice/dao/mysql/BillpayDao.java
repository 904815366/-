package com.example.fundservice.dao.mysql;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillpayDao extends BaseMapper<BillpayPo> {

    @Select("select * from billpay where fstatus = '正常'")
    List<BillpayPo> billpayList();

    @Insert("insert into billpay values (null,now(),#{cgdid},#{gysid},#{accid}," +
            "#{faccount},#{userid},#{fdecr},#{fstatus})")
    Integer addBillpay(BillpayPo billpayPo);

    @Update("update billpay set fstatus='销毁' where fno=#{fno}")
    Integer delBillpay(@Param("fno")Integer fno);
}
