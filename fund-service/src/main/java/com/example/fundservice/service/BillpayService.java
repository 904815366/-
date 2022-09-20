package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdDao;
import com.example.fundservice.dao.mysql.BillpayDao;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.web.controller.dto.BillpayDto;
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

    public List<BillpayListDto> billpayList() {
        List<BillpayListDto> billpayListDtos = new ArrayList<>();
        List<BillpayPo> billpayPoList = billpayDao.billpayList();
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

    public BillmsgcgdPo getCgd(Long cgdid) {
        return billmsgcgdDao.getCgd(cgdid);
    }

    public BillmsgcgdPo getCgdByStatus(Long cgdid) {
        return billmsgcgdDao.getCgdByStatus(cgdid);
    }

    public void delCgd(Long cgdid) {
        billmsgcgdDao.delCgd(cgdid);
    }

    public Integer addBillpay(BillpayPo billpayPo) {
        return billpayDao.addBillpay(billpayPo);
    }

    public Integer addCgd(BillmsgcgdPo billmsgcgdPo) {
        return billmsgcgdDao.addCgd(billmsgcgdPo);
    }

    public void delBillpay(String fnos) {
        String[] fnoArr = fnos.split(",");
        for (String fno : fnoArr) {
            billpayDao.delBillpay(Long.parseLong(fno));
        }
    }

    public BillpayDto getBillpayByStatus(Long fno) {
        BillpayPo billpayPo = billpayDao.getBillpayByStatus(fno);
        if (billpayPo==null){
            return null;
        }else {
            //供应商
            Long gysid = billpayPo.getGysid();
            //结算账户
            Long accid = billpayPo.getAccid();
            //制单人
            Long userid = billpayPo.getUserid();
            //dto
            BillpayDto billpayDto = new BillpayDto();
//            billpayDto.setGysname();
            billpayDto.setFtime(billpayPo.getFtime());
            billpayDto.setFno(billpayPo.getFno());
//            billpayDto.setAccname();
            billpayDto.setFaccount(billpayPo.getFaccount());
            billpayDto.setPaytype("现金");
            billpayDto.setFdecr(billpayPo.getFdecr());
//            billpayDto.setUsername();
            return billpayDto;
        }
    }
}
