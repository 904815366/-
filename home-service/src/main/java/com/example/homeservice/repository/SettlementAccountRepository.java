package com.example.homeservice.repository;


import com.alibaba.spring.util.ObjectUtils;
import com.example.homeservice.dao.mysql.SettlementAccountDao;
import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeserviceapi.fo.SettlementAccountFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

@Repository
@Slf4j
public class SettlementAccountRepository {

    @Resource
    private SettlementAccountDao settlementAccountDao;

    public void modifySettlement(SettlementAccountPo po) {
        settlementAccountDao.save(po);
    }
}
