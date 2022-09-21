package com.example.homeservice.service;

import com.example.homeservice.dao.mysql.SettlementAccountDao;
import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeservice.dao.redis.SettlementAccountRedis;
import com.example.homeservice.dao.redis.po.SettlementAccountRedisPo;
import com.example.homeservice.web.converter.SettlementAccountConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
@Transactional
@Slf4j
public class SettlementAccountService {
    @Resource
    private SettlementAccountRedis settlementAccountRedis;
    @Resource
    private SettlementAccountDao settlementAccountDao;
    @Resource
    private SettlementAccountConverter settlementAccountConverter;
    public SettlementAccountPo queryNameById(Long id) {
        SettlementAccountRedisPo redisPo = settlementAccountRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)){
            return settlementAccountConverter.from(redisPo);
        }
        SettlementAccountPo po = settlementAccountDao.findById(id).orElseThrow(() -> new RuntimeException("根据ID未查询到客户名字"));
        settlementAccountRedis.save(settlementAccountConverter.from(po));
        return po;
    }
}
