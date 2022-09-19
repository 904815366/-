package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillpayDao;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillpayService {
    @Resource
    private BillpayDao billpayDao;

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
}
