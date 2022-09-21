package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdDao;
import com.example.fundservice.dao.mysql.BillreceDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.web.controller.dto.BillreceDto;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import com.example.homeserviceapi.http.CustomersServiceClient;
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
public class BillreceService {
    @Resource
    private BillreceDao billreceDao;
    @Resource
    private BillmsgchdDao billmsgchdDao;
    @Resource
    private CustomersServiceClient customersServiceClient;//客人
    @Resource
    private SettlementServiceClient settlementServiceClient;//结算账户
    @Resource
    private UsersServiceClient usersServiceClient;//用户

    public List<BillreceListDto> billreceList() {
        List<BillreceListDto> billreceListDtos = new ArrayList<>();
        List<BillrecePo> billrecePoList = billreceDao.billreceList();
        for (BillrecePo billrecePo : billrecePoList) {
            BillreceListDto billreceListDto = new BillreceListDto();
            billreceListDto.setStime(billrecePo.getStime());
            billreceListDto.setSno(billrecePo.getSno());
            billreceListDto.setSaccount(billrecePo.getSaccount());
            billreceListDto.setSdecr(billrecePo.getSdecr());
            //通过出货单id获取cstid,通过auth-service-api获取cstname
            ResponseResult<String> cstNameRes = customersServiceClient.queryNameById(billmsgchdDao.getCstid(billrecePo.getChdid()));
            billreceListDto.setCstname(cstNameRes.getData());
            //通过auth-service-api获取username
            ResponseResult<String> userNameRes = usersServiceClient.queryNameById(billrecePo.getUserid());
            billreceListDto.setUsername(userNameRes.getData());
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
            BillreceDto billreceDto = new BillreceDto();
            billreceDto.setCstname(customersServiceClient.queryNameById(billrecePo.getCstid()).getData());
            billreceDto.setStime(billrecePo.getStime());
            billreceDto.setSno(billrecePo.getSno());
            billreceDto.setAccname(settlementServiceClient.queryAccountById(billrecePo.getAccid()).getData());
            billreceDto.setSaccount(billrecePo.getSaccount());
            billreceDto.setPaytype("现金");
            billreceDto.setSdecr(billrecePo.getSdecr());
            billreceDto.setUsername(usersServiceClient.queryNameById(billrecePo.getUserid()).getData());
            return billreceDto;
        }
    }
}
