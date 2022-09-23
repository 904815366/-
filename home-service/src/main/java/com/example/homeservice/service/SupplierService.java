package com.example.homeservice.service;

import com.example.homeservice.dao.mysql.SupplierDao;
import com.example.homeservice.dao.mysql.po.SupplierPo;
import com.example.homeservice.dao.redis.SupplierRedis;
import com.example.homeservice.dao.redis.po.SupplierRedisPo;
import com.example.homeservice.web.converter.SupplierConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
@Transactional
@Slf4j
public class SupplierService {
    @Resource
    private SupplierRedis supplierRedis;
    @Resource
    private SupplierDao supplierDao;
    @Resource
    private SupplierConverter supplierConverter;

    public SupplierPo queryNameById(Long id) {
        SupplierRedisPo redisPo = supplierRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)){
            return supplierConverter.from(redisPo);
        }
        SupplierPo po = supplierDao.findById(id).orElseThrow(() -> new RuntimeException("根据ID未查询到客户名字"));
        supplierRedis.save(supplierConverter.from(po));
        return po;
    }
}
