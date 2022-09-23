package com.example.homeservice.service;

import com.example.homeservice.dao.mysql.SettlementAccountDao;
import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeservice.dao.redis.SettlementAccountRedis;
import com.example.homeservice.dao.redis.po.SettlementAccountRedisPo;
import com.example.homeservice.repository.SettlementAccountRepository;
import com.example.homeservice.web.converter.SettlementAccountConverter;
import com.example.homeserviceapi.fo.SettlementAccountFo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private SettlementAccountRepository settlementAccountRepository;

    public SettlementAccountPo queryNameById(Long id) {
        SettlementAccountRedisPo redisPo = settlementAccountRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)) {
            return settlementAccountConverter.from(redisPo);
        }
        SettlementAccountPo po = settlementAccountDao.findById(id).orElseThrow(() -> new RuntimeException("根据ID未查询到客户名字"));
        settlementAccountRedis.save(settlementAccountConverter.from(po));
        return po;
    }

    public void modifySettlement(SettlementAccountFo fo) {
        RLock lock = redissonClient.getLock("modifySettlement");
        try {
            lock.lock();
            log.info("modifySettlement方法上锁了");
            SettlementAccountPo po = settlementAccountDao.findById(fo.getId()).orElseThrow(() -> new NullPointerException("根据ID未查询到结帐账号"));
            Double balance = null;
            if (StringUtils.isEmpty(fo.getBalance())) {
                log.info("未传余额");
                balance = po.getBalance();
            } else {
                log.info("传了金额, po金额 + fo金额");
                balance = po.getBalance() + fo.getBalance();
            }

            if (balance < 0) throw new RuntimeException("余额不足,结账失败");

            po.setBalance(balance);
            po.setAccount(StringUtils.isEmpty(fo.getAccount()) ? po.getAccount() : fo.getAccount());
            po.setOpeningBalance(StringUtils.isEmpty(fo.getOpeningBalance()) ? po.getOpeningBalance() : fo.getOpeningBalance());
            po.setAccountTypeId(StringUtils.isEmpty(fo.getAccountTypeId()) ? po.getAccountTypeId() : fo.getAccountTypeId());
            po.setStatus(StringUtils.isEmpty(fo.getStatus()) ? po.getStatus() : fo.getStatus());
            System.out.println("modifySettlement:" + po);
            settlementAccountRepository.modifySettlement(po);
            settlementAccountRedis.deleteById(fo.getId());
            log.info("modifySettlement执行成功,从redis中删除ID为{}的对象", fo.getId());
            lock.unlock();
            log.info("modifySettlement方法解锁了");
        }catch (Exception e){
            e.printStackTrace();
            lock.unlock();
            throw new RuntimeException("余额不足,结账失败");
        }


    }
}
