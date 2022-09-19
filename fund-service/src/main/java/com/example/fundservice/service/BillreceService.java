package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillpayDao;
import com.example.fundservice.dao.mysql.BillreceDao;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillreceService {
    @Resource
    private BillreceDao billreceDao;
    public List<BillreceListDto> list() {
        List<BillreceListDto> billreceListDtos = new ArrayList<>();
        List<BillrecePo> billrecePoList = billreceDao.list();
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
}
