package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdDao;
import com.example.fundservice.dao.mysql.BillreceDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@GlobalTransactional
public class BillmsgchdService {
    @Resource
    private BillreceDao billreceDao;
    @Resource
    private BillmsgchdDao billmsgchdDao;
    //出货单消息重复
    public BillmsgchdPo getChd(Long chdid) {
        return billmsgchdDao.getChd(chdid);
    }
    //未审核的出货单
    public BillmsgchdPo getChdByStatus(Long chdid) {
        return billmsgchdDao.getChdByStatus(chdid);
    }
    //删除出货单消息(改状态为已通过)
    public void delChd(Long chdid) {
        billmsgchdDao.delChd(chdid);
    }
    //新增出货单消息
    public Integer addChd(BillmsgchdPo billmsgchdPo) {
        return billmsgchdDao.addChd(billmsgchdPo);
    }
}
