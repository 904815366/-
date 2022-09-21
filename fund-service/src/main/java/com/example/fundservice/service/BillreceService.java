package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdDao;
import com.example.fundservice.dao.mysql.BillreceDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.web.controller.dto.BillreceDto;
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
            //通过出货单id获取cstid,通过auth-service-api获取gysname
            Long cstid = billmsgchdDao.getCstid(billrecePo.getCstid());
            //billreceListDto.setCstname();

            //通过auth-service-api获取username
            Long userid = billrecePo.getUserid();
            //billreceListDto.setUsername();
            billreceListDtos.add(billreceListDto);
        }
        return billreceListDtos;
    }

    public BillmsgchdPo getChd(Long chdid) {
        return billmsgchdDao.getChd(chdid);
    }

    public BillmsgchdPo getChdByStatus(Long chdid) {
        return billmsgchdDao.getChdByStatus(chdid);
    }

    public void delChd(Long chdid) {
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
            billreceDao.delBillrece(Long.parseLong(sno));
        }
    }

    public BillreceDto getBillreceByStatus(Long sno) {
        BillrecePo billrecePo = billreceDao.getBillreceByStatus(sno);
        if (billrecePo==null){
            return null;
        }else {
            //客户
            Long cstid = billrecePo.getCstid();
            //结算账户
            Long accid = billrecePo.getAccid();
            //制单人
            Long userid = billrecePo.getUserid();
            //dto
            BillreceDto billreceDto = new BillreceDto();
//            billreceDto.setGysname();
            billreceDto.setStime(billrecePo.getStime());
            billreceDto.setSno(billrecePo.getSno());
//            billreceDto.setAccname();
            billreceDto.setSaccount(billrecePo.getSaccount());
            billreceDto.setPaytype("现金");
            billreceDto.setSdecr(billrecePo.getSdecr());
//            billreceDto.setUsername();
            return billreceDto;
        }
    }
}
