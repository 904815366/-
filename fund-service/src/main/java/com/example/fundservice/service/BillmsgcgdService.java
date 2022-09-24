package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdDao;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.woniuxy.purchaseserviceapi.client.PurchaseClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@GlobalTransactional
public class BillmsgcgdService {
    @Resource
    private BillmsgcgdDao billmsgcgdDao;
    @Resource
    private PurchaseClient purchaseClient;
    //采购单消息是否重复
    public BillmsgcgdPo getCgd(Long cgdid) {
        return billmsgcgdDao.getCgd(cgdid);
    }
    //未审核的采购单
    public BillmsgcgdPo getCgdByStatus(Long cgdid) {
        return billmsgcgdDao.getCgdByStatus(cgdid);
    }
    //删除(改状态为已通过)采购单消息
    public void delCgd(Long cgdid) {
        billmsgcgdDao.delCgd(cgdid);
    }
    //新增采购单消息
    public Integer addCgd(BillmsgcgdPo billmsgcgdPo) {
        return billmsgcgdDao.addCgd(billmsgcgdPo);
    }
    //确认已付款
    public void updPur(Long cgdid) {
        purchaseClient.modifyPaymentStatus(cgdid,1);
    }
}
