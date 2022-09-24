package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdreDao;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillmsgcgdrePo;
import com.woniuxy.purchaseserviceapi.client.PurchaseClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@GlobalTransactional
public class BillmsgcgdreService {
    @Resource
    private BillmsgcgdreDao billmsgcgdreDao;
    @Resource
    private PurchaseClient purchaseClient;
    //退货单是否重复
    public BillmsgcgdrePo getThd(Long reid) {
        return billmsgcgdreDao.getThd(reid);
    }
    //新增采购退货单
    public Integer addThd(BillmsgcgdrePo billmsgcgdrePo) {
        return billmsgcgdreDao.addThd(billmsgcgdrePo);
    }
    //未审核的退货单
    public BillmsgcgdrePo getThdByStatus(Long cgdid){
        return billmsgcgdreDao.getThdByStatus(cgdid);
    }
    //删除(改状态为已通过)退货单
    public void delCgdRe(Long cgdid) {
        billmsgcgdreDao.delThd(cgdid);
    }

    public Long getReid(Long cgdid) {
        return billmsgcgdreDao.getReid(cgdid);
    }
    //通知采购员,退款已收
    public void cgdRe(Long reid) {
        purchaseClient.modifyInPay(reid, 1);
    }
}
