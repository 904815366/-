package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdreDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import com.example.shipment.api.ShipmentClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@GlobalTransactional
public class BillmsgchdreService {
    @Resource
    private BillmsgchdreDao billmsgchdreDao;
    @Resource
    private ShipmentClient shipmentClient;
    //退货单是否重复
    public BillmsgchdrePo getThd(Long reid) {
        return billmsgchdreDao.getThd(reid);
    }
    //新增采购退货单
    public Integer addThd(BillmsgchdrePo billmsgchdrePo) {
        return billmsgchdreDao.addThd(billmsgchdrePo);
    }
    //未审核的退货单
    public BillmsgchdrePo getThdByStatus(Long chdid) {
        return billmsgchdreDao.getThdByStatus(chdid);
    }
    //删除(改状态为已通过)退货单
    public void delChdRe(Long chdid) {
        billmsgchdreDao.delThd(chdid);
    }

    public Long getReid(Long chdid) {
        return billmsgchdreDao.getReid(chdid);
    }
    //通知出货员,退款已付
    public void chdRe(Long reid) {
        shipmentClient.setReturnSalesAsStatus(reid);
    }
}
