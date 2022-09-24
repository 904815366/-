package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdreDao;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillmsgcgdrePo;
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
    //退货单是否重复
    public BillmsgcgdrePo getThd(Long reid) {
        return billmsgcgdreDao.getThd(reid);
    }
    //新增采购退货单
    public Integer addThd(BillmsgcgdrePo billmsgcgdrePo) {
        return billmsgcgdreDao.addThd(billmsgcgdrePo);
    }

}
