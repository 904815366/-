package com.woniu.dao;

import com.woniu.dao.po.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xk
 * @since 2022-09-25
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
