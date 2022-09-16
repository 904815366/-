package com.woniu.dao;

import com.woniu.dao.po.Cusorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.web.fo.CusorderFo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Mapper
public interface CusorderMapper extends BaseMapper<Cusorder> {

    List<Cusorder> querypageByfo(CusorderFo cusorderFo);

}
