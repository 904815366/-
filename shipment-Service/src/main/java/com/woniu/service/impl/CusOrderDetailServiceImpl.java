package com.woniu.service.impl;

import com.woniu.dao.po.CusOrderDetail;
import com.woniu.dao.CusOrderDetailMapper;
import com.woniu.repository.CusorderDetailRepository;
import com.woniu.service.CusOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Service
public class CusOrderDetailServiceImpl extends ServiceImpl<CusOrderDetailMapper, CusOrderDetail> implements CusOrderDetailService {
   @Resource
   private CusorderDetailRepository cusorderDetailRepository;

    @Override
    public void deltODetailByOid(Long oid) {
        cusorderDetailRepository.deltODetailByOid(oid);
    }
}
