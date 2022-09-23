package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.po.MessagePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao extends BaseMapper<MessagePo> {
    List<MessagePo> selectMessageAll();
    Integer modifyByIdStatus(@Param("id") Long id,@Param("status") String status);
}
