package com.example.fundservice.service;

import com.example.fundservice.dao.mysql.BillmsgchdDao;
import com.example.fundservice.dao.mysql.BillreceDao;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.web.controller.dto.BillreceDto;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import com.example.homeserviceapi.fo.SettlementAccountFo;
import com.example.homeserviceapi.http.CustomersServiceClient;
import com.example.homeserviceapi.http.SettlementServiceClient;
import com.example.homeserviceapi.http.SupplierServiceClient;
import com.example.homeserviceapi.http.UsersServiceClient;
import com.example.homeserviceapi.utils.ResponseResult;
import com.example.shipment.api.ShipmentClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@GlobalTransactional
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
    @Resource
    private ShipmentClient shipmentClient;//出货
    @Resource
    private SupplierServiceClient supplierServiceClient;//供应商

    //收款单列表
    public List<BillreceListDto> billreceList() {
        List<BillreceListDto> billreceListDtos = new ArrayList<>();
        List<BillrecePo> billrecePoList = billreceDao.billreceList();
        for (BillrecePo billrecePo : billrecePoList) {
            BillreceListDto billreceListDto = new BillreceListDto();
            billreceListDto.setStime(billrecePo.getStime());
            billreceListDto.setSno(billrecePo.getSno());
            billreceListDto.setSaccount(billrecePo.getSaccount());
            billreceListDto.setSdecr(billrecePo.getSdecr());
            //分类型  出货收款/采购退款
            if ("出货收款".equals(billrecePo.getType())){
                //通过auth-service-api获取cstname
                ResponseResult<String> cstNameRes = customersServiceClient.queryNameById(billrecePo.getCstid());
                billreceListDto.setCstname(cstNameRes.getData());
            }else if("采购退款".equals(billrecePo.getType())){
                //通过auth-service-api获取gysname
                ResponseResult<String> gysNameRes = supplierServiceClient.queryNameById(billrecePo.getCstid());
                billreceListDto.setCstname(gysNameRes.getData());
            }
            //通过auth-service-api获取username
            ResponseResult<String> userNameRes = usersServiceClient.queryNameById(billrecePo.getUserid());
            billreceListDto.setUsername(userNameRes.getData());
            billreceListDtos.add(billreceListDto);
        }
        return billreceListDtos;
    }

    //新增收款单
    public Integer addBillrece(BillrecePo billrecePo) {
        SettlementAccountFo saFo = new SettlementAccountFo();
        saFo.setId(billrecePo.getAccid());
        saFo.setBalance(billrecePo.getSaccount());
        try {
            settlementServiceClient.modifySettlement(saFo);
            billreceDao.addBillrece(billrecePo);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            return 9;
        }
    }

    //删除收款单(该状态为销毁)
    public void delBillrece(String snos) {
        String[] snoArr = snos.split(",");
        for (String sno : snoArr) {
            billreceDao.delBillrece(Long.parseLong(sno));
        }
    }

    //查看收款单
    public BillreceDto getBillreceByStatus(Long sno) {
        BillrecePo billrecePo = billreceDao.getBillreceByStatus(sno);
        if (billrecePo==null){
            return null;
        }else {
            BillreceDto billreceDto = new BillreceDto();
            if ("出货收款".equals(billrecePo.getType())){
                billreceDto.setCstname(customersServiceClient.queryNameById(billrecePo.getCstid()).getData());
            }else if("采购退款".equals(billrecePo.getType())){
                billreceDto.setCstname(supplierServiceClient.queryNameById(billrecePo.getCstid()).getData());
            }
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
