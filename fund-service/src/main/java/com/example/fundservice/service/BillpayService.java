package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdDao;
import com.example.fundservice.dao.mysql.BillpayDao;
import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.web.controller.dto.BillpayDto;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import com.example.homeserviceapi.http.SettlementServiceClient;
import com.example.homeserviceapi.http.SupplierServiceClient;
import com.example.homeserviceapi.http.UsersServiceClient;
import com.example.homeserviceapi.utils.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BillpayService {
    @Resource
    private BillpayDao billpayDao;
    @Resource
    private BillmsgcgdDao billmsgcgdDao;
    @Resource
    private UsersServiceClient usersServiceClient;//用户
    @Resource
    private SettlementServiceClient settlementServiceClient;//结算账户
    @Resource
    private SupplierServiceClient supplierServiceClient;//供应商

    public List<BillpayListDto> billpayList() {
        List<BillpayListDto> billpayListDtos = new ArrayList<>();
        List<BillpayPo> billpayPoList = billpayDao.billpayList();
        for (BillpayPo billpayPo : billpayPoList) {
            BillpayListDto billpayListDto = new BillpayListDto();
            billpayListDto.setFtime(billpayPo.getFtime());
            billpayListDto.setFno(billpayPo.getFno());
            billpayListDto.setFaccount(billpayPo.getFaccount());
            billpayListDto.setFdecr(billpayPo.getFdecr());
            //通过采购单id获取gysid,通过auth-service-api获取gysname
            ResponseResult<String> gysNameRes = supplierServiceClient.queryNameById(billmsgcgdDao.getGysid(billpayPo.getCgdid()));
            billpayListDto.setGysname(gysNameRes.getData());
            //通过auth-service-api获取username
            ResponseResult<String> userNameRes = usersServiceClient.queryNameById(billpayPo.getUserid());
            billpayListDto.setUsername(userNameRes.getData());
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
            BillpayDto billpayDto = new BillpayDto();
            billpayDto.setGysname(supplierServiceClient.queryNameById(billpayPo.getGysid()).getData());
            billpayDto.setFtime(billpayPo.getFtime());
            billpayDto.setFno(billpayPo.getFno());
            billpayDto.setAccname(settlementServiceClient.queryAccountById(billpayPo.getAccid()).getData());
            billpayDto.setFaccount(billpayPo.getFaccount());
            billpayDto.setPaytype("现金");
            billpayDto.setFdecr(billpayPo.getFdecr());
            billpayDto.setUsername(usersServiceClient.queryNameById(billpayPo.getUserid()).getData());
            return billpayDto;
        }
    }
}
