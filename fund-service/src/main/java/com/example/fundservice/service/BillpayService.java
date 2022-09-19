package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdDao;
import com.example.fundservice.dao.mysql.BillpayDao;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillpayService {
    @Resource
    private BillpayDao billpayDao;
    @Resource
    private BillmsgcgdDao billmsgcgdDao;

    public List<BillpayListDto> list() {
        List<BillpayListDto> billpayListDtos = new ArrayList<>();
        List<BillpayPo> billpayPoList = billpayDao.list();
        for (BillpayPo billpayPo : billpayPoList) {
            BillpayListDto billpayListDto = new BillpayListDto();
            billpayListDto.setFtime(billpayPo.getFtime());
            billpayListDto.setFno(billpayPo.getFno());
            billpayListDto.setFaccount(billpayPo.getFaccount());
            billpayListDto.setFdecr(billpayPo.getFdecr());
            //billpayListDto.setGysname();
            //billpayListDto.setUsername();
            billpayListDtos.add(billpayListDto);
        }
        return billpayListDtos;
    }

    public BillmsgcgdPo getCgd(Integer cgdid) {
        return billmsgcgdDao.getCgd(cgdid);
    }

    public BillmsgcgdPo getCgdByStatus(Integer cgdid) {
        return billmsgcgdDao.getCgdByStatus(cgdid);
    }

    public void updCgd(Integer cgdid) {
        billmsgcgdDao.updCgd(cgdid);
    }

    public Integer add(BillpayPo billpayPo) {
        return billpayDao.add(billpayPo);
    }

    public Integer addCgd(BillmsgcgdPo billmsgcgdPo) {
        return billmsgcgdDao.addCgd(billmsgcgdPo);
    }
}
