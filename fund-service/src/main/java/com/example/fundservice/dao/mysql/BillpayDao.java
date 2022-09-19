package com.example.fundservice.dao.mysql;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface BillpayDao extends BaseMapper<BillpayPo> {

    @Select("select * from billpay")
    List<BillpayPo> list();
}
