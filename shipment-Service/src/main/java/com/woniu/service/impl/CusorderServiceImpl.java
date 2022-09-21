package com.woniu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.dao.po.Cusorder;
import com.woniu.dao.CusorderMapper;
import com.woniu.repository.CusorderDetailRepository;
import com.woniu.repository.CusorderRepository;
import com.woniu.repository.converter.CusorderAndDetailFoConverter;
import com.woniu.repository.converter.CusorderConverter;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.service.CusorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.web.fo.AddCusAndDetailFo;
import com.woniu.web.fo.CusorderFo;
import com.woniu.web.fo.UpSiteFo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    @Resource
    private CusorderDetailRepository cusorderDetailRepository;

    @Resource
    private CusorderAndDetailFoConverter cusorderAndDetailFoConverter;

    @Resource
    private CusorderConverter converter;

//    分页查询业务方法
    public PageInfo<CusorderDto> qurAllPageByFo(CusorderFo cusorderFo) {

        PageHelper.startPage(cusorderFo.getPageNum(),cusorderFo.getPageSize());

        List<Cusorder> pos = cusorderRepository.getPageAllByfo(cusorderFo);

        PageInfo<Cusorder> cusorderPageInfo = new PageInfo<>(pos);

        PageInfo<CusorderDto> pageDto = converter.from(cusorderPageInfo);

        return pageDto;
    }

//    新增方法
//    @Transactional
    public void addCus(AddCusAndDetailFo addCusAndDetailFo) {
        Cusorder cusorder = cusorderAndDetailFoConverter.from(addCusAndDetailFo);
        if (ObjectUtils.isEmpty(addCusAndDetailFo.getId())){
            cusorderRepository.AddCus(cusorder);
        }
       cusorderRepository.upcus(cusorder);
        addCusAndDetailFo.getCusOrderDetails().forEach(e->{
            cusorderDetailRepository.AddCusDetail(e);
        });
    }

    @Override
    public void upSite(UpSiteFo siteFo) {
        cusorderRepository.upSite(siteFo);
    }
}
