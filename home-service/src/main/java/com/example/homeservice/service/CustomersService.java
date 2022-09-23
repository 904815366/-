package com.example.homeservice.service;

import com.example.homeservice.dao.mysql.CustomersDao;
import com.example.homeservice.dao.mysql.po.CustomersPo;
import com.example.homeservice.dao.redis.CustomersRedis;
import com.example.homeservice.dao.redis.po.CustomersRedisPo;
import com.example.homeservice.web.converter.CustomersConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
@Transactional
@Slf4j
public class CustomersService {
    @Resource
    private CustomersRedis customersRedis;
    @Resource
    private CustomersDao customersDao;
    @Resource
    private CustomersConverter customersConverter;

    public CustomersPo queryNameById(Long id) {
        CustomersRedisPo redisPo = customersRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)){
            return customersConverter.from(redisPo);
        }
      CustomersPo po = customersDao.findById(id).orElseThrow(() -> new RuntimeException("根据ID未查询到客户名字"));
        customersRedis.save(customersConverter.from(po));
        return po;
    }
}
