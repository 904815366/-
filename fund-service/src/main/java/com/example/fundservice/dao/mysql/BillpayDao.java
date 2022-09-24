package com.example.fundservice.dao.mysql;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillpayDao extends BaseMapper<BillpayPo> {

    @Select("select * from billpay where fstatus = '正常' and faccount >0")
    List<BillpayPo> billpayList();

    @Insert("insert into billpay values (null,now(),'暂未修改',#{cgdid},#{gysid},#{accid}," +
            "#{faccount},#{userid},#{fdecr},#{fstatus})")
    Integer addBillpay(BillpayPo billpayPo);

    @Update("update billpay set fstatus='销毁' where fno=#{fno}")
    Integer delBillpay(@Param("fno")Long fno);

    @Select("select * from billpay where fno=#{fno} and fstatus='正常' and faccount >0")
    BillpayPo getBillpayByStatus(@Param("fno") Long fno);
}
