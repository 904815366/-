package com.woniu.service;

import com.github.pagehelper.PageInfo;
import com.woniu.dao.po.Cusorder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.web.fo.CusorderFo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
public interface CusorderService extends IService<Cusorder> {

    PageInfo<CusorderDto> qurAllPageByFo(CusorderFo cusorderFo);

}
