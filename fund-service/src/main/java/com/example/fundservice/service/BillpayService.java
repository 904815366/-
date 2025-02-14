package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgcgdDao;
import com.example.fundservice.dao.mysql.BillpayDao;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.web.controller.dto.BillpayDto;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import com.example.homeserviceapi.fo.SettlementAccountFo;
import com.example.homeserviceapi.http.CustomersServiceClient;
import com.example.homeserviceapi.http.SettlementServiceClient;
import com.example.homeserviceapi.http.SupplierServiceClient;
import com.example.homeserviceapi.http.UsersServiceClient;
import com.example.homeserviceapi.utils.ResponseResult;
import com.woniuxy.purchaseserviceapi.client.PurchaseClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@GlobalTransactional
public class BillpayService {
    @Resource
    private BillpayDao billpayDao;
    @Resource
    private UsersServiceClient usersServiceClient;//用户
    @Resource
    private SettlementServiceClient settlementServiceClient;//结算账户
    @Resource
    private SupplierServiceClient supplierServiceClient;//供应商
    @Resource
    private CustomersServiceClient customersServiceClient;//客人
    @Resource
    private PurchaseClient purchaseClient;//采购

    //付款单列表
    public List<BillpayListDto> billpayList() {
        List<BillpayListDto> billpayListDtos = new ArrayList<>();
        List<BillpayPo> billpayPoList = billpayDao.billpayList();
        for (BillpayPo billpayPo : billpayPoList) {
            //分类型  采购付款/出货退款
            BillpayListDto billpayListDto = new BillpayListDto();
            billpayListDto.setFtime(billpayPo.getFtime());
            billpayListDto.setFno(purchaseClient.getInvoiceNumber(billpayPo.getFno()).getData());
            billpayListDto.setFaccount(billpayPo.getFaccount());
            billpayListDto.setFdecr(billpayPo.getFdecr());
            if ("采购付款".equals(billpayPo.getType())){
                //通过auth-service-api获取gysname
                ResponseResult<String> gysNameRes = supplierServiceClient.queryNameById(billpayPo.getGysid());
                billpayListDto.setGysname(gysNameRes.getData());
            }else if("出货退款".equals(billpayPo.getType())){
                //通过auth-service-api获取ctsname
                ResponseResult<String> cstNameRes = customersServiceClient.queryNameById(billpayPo.getGysid());
                billpayListDto.setGysname(cstNameRes.getData());
            }
            //通过auth-service-api获取username
            ResponseResult<String> userNameRes = usersServiceClient.queryNameById(billpayPo.getUserid());
            billpayListDto.setUsername(userNameRes.getData());
            billpayListDtos.add(billpayListDto);
        }
        return billpayListDtos;
    }
    //新增付款单
    public Integer addBillpay(BillpayPo billpayPo) {
        SettlementAccountFo saFo = new SettlementAccountFo();
        saFo.setId(billpayPo.getAccid());
        saFo.setBalance(-billpayPo.getFaccount());
        try {
            settlementServiceClient.modifySettlement(saFo);
            billpayDao.addBillpay(billpayPo);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            return 9;
        }
    }
    //(批量)删除付款单
    public void delBillpay(String fnos) {
        String[] fnoArr = fnos.split(",");
        for (String fno : fnoArr) {
            billpayDao.delBillpay(Long.parseLong(fno));
        }
    }
    //查看付款单
    public BillpayDto getBillpayByStatus(Long fno) {
        BillpayPo billpayPo = billpayDao.getBillpayByStatus(fno);
        if (billpayPo==null){
            return null;
        }else {
            BillpayDto billpayDto = new BillpayDto();
            if ("采购付款".equals(billpayPo.getType())){
                billpayDto.setGysname(supplierServiceClient.queryNameById(billpayPo.getGysid()).getData());
            }else if("出货退款".equals(billpayPo.getType())){
                billpayDto.setGysname(customersServiceClient.queryNameById(billpayPo.getGysid()).getData());
            }
            billpayDto.setFtime(billpayPo.getFtime());
            billpayDto.setFno(purchaseClient.getInvoiceNumber(billpayPo.getFno()).getData());
            billpayDto.setAccname(settlementServiceClient.queryAccountById(billpayPo.getAccid()).getData());
            billpayDto.setFaccount(billpayPo.getFaccount());
            billpayDto.setPaytype("现金");
            billpayDto.setFdecr(billpayPo.getFdecr());
            billpayDto.setUsername(usersServiceClient.queryNameById(billpayPo.getUserid()).getData());
            return billpayDto;
        }
    }
}
