package com.example.fundservice.web.controller;

import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.service.BillpayService;
import com.example.fundservice.util.ResponseResult;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BillpayController {
    @Resource
    private BillpayService billpayService;
    //付款单列表
    @GetMapping("/billpay/list")
    public ResponseResult<List<BillpayListDto>> billpayList() {
        List<BillpayListDto> billpayListDtoList = billpayService.billpayList();
        return new ResponseResult(200, "OK", billpayListDtoList);
    }
    //新增付款单(编号fno/日期ftime/采购单编号cgdid
    //         供应商gysid/结算账户accid/金额faccount
    //         制单人userid/描述fdecr/状态fstatus)
    @PostMapping("/billpay/addBillpay")
    public ResponseResult<Void> addBillpay(@RequestParam("cgdid")Integer cgdid,
                                           @RequestParam("userid")Integer userid,
                                           @RequestParam("fdecr")String fdecr){
        BillmsgcgdPo cgd = billpayService.getCgdByStatus(cgdid);
        if (cgd==null){
            return new ResponseResult<Void>(0, "采购单不存在" );
        }else {
            Integer gysid = cgd.getGysid();
            Integer accid = cgd.getAccid();
            Double account = cgd.getAccount();

            BillpayPo billpayPo = new BillpayPo();
            billpayPo.setCgdid(cgdid);
            billpayPo.setUserid(userid);
            billpayPo.setFdecr(fdecr);
            billpayPo.setFstatus("正常");
            billpayPo.setGysid(gysid);
            billpayPo.setAccid(accid);
            billpayPo.setFaccount(account);

            Integer add = billpayService.addBillpay(billpayPo);
            if (add==1){
                billpayService.delCgd(cgdid);
                return new ResponseResult<Void>(200, "OK" );
            }else {
                return new ResponseResult<Void>(00, "系统维护中" );
            }
        }
    }
    //删除付款单(批量)     fnos
    @PostMapping("/billpay/delBillpay")
    public ResponseResult<Void> delBillpay(@RequestParam("fnos")String fnos){
        billpayService.delBillpay(fnos);
        return new ResponseResult<Void>(200, "OK" );
    }
    //修改付款单     fno     accid
    @PostMapping("/billpay/updBillpay")
    public ResponseResult<Void> updBillpay(){
        return new ResponseResult<Void>(200, "OK" );
    }
    //采购单消息
    @PostMapping("/billpay/addBillpayMsg")
    public ResponseResult<Void> getCgdMsg(@RequestParam("cgdid")Integer cgdid,
                                          @RequestParam("gysid")Integer gysid,
                                          @RequestParam("accid")Integer accid,
                                          @RequestParam("account")Double account){
        BillmsgcgdPo cgd = billpayService.getCgd(cgdid);
        if (cgd!=null){
            return new ResponseResult<>(0, "重复发送");
        }
        BillmsgcgdPo billmsgcgdPo = new BillmsgcgdPo(cgdid, gysid, accid, account,"未审核");
        Integer addCgd = billpayService.addCgd(billmsgcgdPo);
        if (addCgd==1){
            return new ResponseResult<>(200,"已保存");
        }else {
            return new ResponseResult<>(00,"出错了");
        }
    }
}
