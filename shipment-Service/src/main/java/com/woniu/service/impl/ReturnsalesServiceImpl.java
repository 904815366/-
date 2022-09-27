package com.woniu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.dao.po.Returnsales;
import com.woniu.dao.ReturnsalesMapper;
import com.woniu.repository.ReturnsalesRepository;
import com.woniu.repository.dto.ReturnsalesDto;
import com.woniu.service.ReturnsalesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.web.fo.PageReturnSalesFo;
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
public class ReturnsalesServiceImpl extends ServiceImpl<ReturnsalesMapper, Returnsales> implements ReturnsalesService {

    @Resource
    private ReturnsalesRepository returnsalesRepository;

    @Resource
    private ReturnsalesMapper returnsalesMapper;

    public PageInfo<ReturnsalesDto> getPageRetuensaless(PageReturnSalesFo returnSalesFo) {
        PageHelper.startPage(returnSalesFo.getPageNum(), returnSalesFo.getPageSize());
        List<ReturnsalesDto> pageReturnSales = returnsalesRepository.getPageReturnSales(returnSalesFo);
        return new PageInfo<>(pageReturnSales);
    }

    @Override
    public void addReturnsale(Returnsales returnsales) {
        returnsalesMapper.insert(returnsales);
    }
}
