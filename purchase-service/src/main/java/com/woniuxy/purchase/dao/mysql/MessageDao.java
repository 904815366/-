package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.po.MessagePo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao extends BaseMapper<MessagePo> {
}
