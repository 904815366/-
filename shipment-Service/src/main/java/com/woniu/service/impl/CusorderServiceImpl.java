package com.woniu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.dao.po.Cusorder;
import com.woniu.dao.CusorderMapper;
import com.woniu.repository.CusorderRepository;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.service.CusorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.web.fo.CusorderFo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Service
public class CusorderServiceImpl extends ServiceImpl<CusorderMapper, Cusorder> implements CusorderService {

    @Resource
    private CusorderRepository cusorderRepository;

    public PageInfo<CusorderDto> qurAllPageByFo(CusorderFo cusorderFo) {

        PageHelper.startPage(cusorderFo.getPageNum(),cusorderFo.getPageSize());

        List<CusorderDto> dtos = cusorderRepository.getPageAllByfo(cusorderFo);

        PageInfo<CusorderDto> cusorderDtoPageInfo = new PageInfo<>(dtos);
        return cusorderDtoPageInfo;
    }
}
