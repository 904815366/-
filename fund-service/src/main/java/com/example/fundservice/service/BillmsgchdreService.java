package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdreDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
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
    //退货单是否重复
    public BillmsgchdrePo getThd(Long reid) {
        return billmsgchdreDao.getThd(reid);
    }
    //新增采购退货单
    public Integer addThd(BillmsgchdrePo billmsgchdrePo) {
        return billmsgchdreDao.addThd(billmsgchdrePo);
    }
}
