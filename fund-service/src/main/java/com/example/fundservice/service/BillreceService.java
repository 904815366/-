package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdDao;
import com.example.fundservice.dao.mysql.BillreceDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillreceService {
    @Resource
    private BillreceDao billreceDao;
    @Resource
    private BillmsgchdDao billmsgchdDao;

    public List<BillreceListDto> billreceList() {
        List<BillreceListDto> billreceListDtos = new ArrayList<>();
        List<BillrecePo> billrecePoList = billreceDao.billreceList();
        for (BillrecePo billrecePo : billrecePoList) {
            BillreceListDto billreceListDto = new BillreceListDto();
            billreceListDto.setStime(billrecePo.getStime());
            billreceListDto.setSno(billrecePo.getSno());
            billreceListDto.setSaccount(billrecePo.getSaccount());
            billreceListDto.setSdecr(billrecePo.getSdecr());
            //billreceListDto.setCstname();
            //billreceListDto.setUsername();
            billreceListDtos.add(billreceListDto);
        }
        return billreceListDtos;
    }

    public BillmsgchdPo getChd(Integer chdid) {
        return billmsgchdDao.getChd(chdid);
    }

    public BillmsgchdPo getChdByStatus(Integer chdid) {
        return billmsgchdDao.getChdByStatus(chdid);
    }

    public void delChd(Integer chdid) {
        billmsgchdDao.delChd(chdid);
    }

    public Integer addBillrece(BillrecePo billrecePo) {
        return billreceDao.addBillrece(billrecePo);
    }

    public Integer addChd(BillmsgchdPo billmsgchdPo) {
        return billmsgchdDao.addChd(billmsgchdPo);
    }

    public void delBillrece(String snos) {
        String[] snoArr = snos.split(",");
        for (String sno : snoArr) {
            billreceDao.delBillrece(Integer.parseInt(sno));
        }
    }
}
