package com.example.fundservice.web.controller;

import com.example.fundservice.dao.mysql.po.BillmsgcgdPo;
import com.example.fundservice.dao.mysql.po.BillmsgcgdrePo;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import com.example.fundservice.dao.mysql.po.BillpayPo;
import com.example.fundservice.service.BillmsgcgdService;
import com.example.fundservice.service.BillmsgcgdreService;
import com.example.fundservice.service.BillmsgchdreService;
import com.example.fundservice.service.BillpayService;
import com.example.fundservice.util.ResponseResult;
import com.example.fundservice.web.controller.dto.BillpayDto;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BillpayController {
    @Resource
    private BillpayService billpayService;
    @Resource
    private BillmsgcgdService billmsgcgdService;
    @Resource
    private BillmsgchdreService billmsgchdreService;
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
    public ResponseResult<Void> addBillpay(@RequestParam("cgdid")Long cgdid,
                                           @RequestParam("userid")Long userid,
                                           @RequestParam("fdecr")String fdecr){
        BillmsgcgdPo cgd = billmsgcgdService.getCgdByStatus(cgdid);
        BillmsgchdrePo chdRe = billmsgchdreService.getThdByStatus(cgdid);
        if (cgd==null){
            if (chdRe==null){
                return new ResponseResult<Void>(0, "源单不存在" );
            }else {
                Long gysid = chdRe.getCstid();
                Long accid = chdRe.getAccid();
                Double account = chdRe.getAccount();
                BillpayPo billpayPo = new BillpayPo();
                billpayPo.setCgdid(cgdid);
                billpayPo.setUserid(userid);
                billpayPo.setFdecr(fdecr);
                billpayPo.setFstatus("正常");
                billpayPo.setGysid(gysid);
                billpayPo.setAccid(accid);
                billpayPo.setFaccount(account);
                billpayPo.setType("出货退款");
                Integer add = billpayService.addBillpay(billpayPo);
                if (add==1){
                    billmsgchdreService.delChdRe(cgdid);
                    //openfeign告诉出货员,退款已付
                    billmsgchdreService.chdRe(billmsgchdreService.getReid(cgdid));
                    return new ResponseResult<Void>(200, "OK" );
                }else {
                    return new ResponseResult<Void>(1, "系统维护中" );
                }
            }
        }else {
            Long gysid = cgd.getGysid();
            Long accid = cgd.getAccid();
            Double account = cgd.getAccount();

            BillpayPo billpayPo = new BillpayPo();
            billpayPo.setCgdid(cgdid);
            billpayPo.setUserid(userid);
            billpayPo.setFdecr(fdecr);
            billpayPo.setFstatus("正常");
            billpayPo.setGysid(gysid);
            billpayPo.setAccid(accid);
            billpayPo.setFaccount(account);
            billpayPo.setType("采购付款");

            Integer add = billpayService.addBillpay(billpayPo);
            if (add==1){
                billmsgcgdService.delCgd(cgdid);
                //openfeign告诉采购员,付款已付
                billmsgcgdService.updPur(billmsgchdreService.getReid(cgdid));
                return new ResponseResult<Void>(200, "OK" );
            }else {
                return new ResponseResult<Void>(1, "系统维护中" );
            }
        }
    }
    //删除付款单(批量)     fnos
    @PostMapping("/billpay/delBillpay")
    public ResponseResult<Void> delBillpay(@RequestParam("fnos")String fnos){
        billpayService.delBillpay(fnos);
        return new ResponseResult<Void>(200, "OK" );
    }
    //查看付款单     fno
    @PostMapping("/billpay/updBillpay")
    public ResponseResult<BillpayDto> updBillpay(@RequestParam("fno")Long fno){
        BillpayDto billpayDto = billpayService.getBillpayByStatus(fno);
        if (billpayDto==null){
            return new ResponseResult<BillpayDto>(0, "付款单不存在" ,null);
        }
        return new ResponseResult<BillpayDto>(200, "OK" ,billpayDto);
    }
    //采购单消息
    @PostMapping("/billpay/addBillpayMsg")
    public ResponseResult<Void> getCgdMsg(@RequestParam("cgdid")Long cgdid,
                                          @RequestParam("gysid")Long gysid,
                                          @RequestParam("accid")Long accid,
                                          @RequestParam("account")Double account){
        BillmsgcgdPo cgd = billmsgcgdService.getCgd(cgdid);
        if (cgd!=null){
            return new ResponseResult<>(0, "重复发送");
        }
        BillmsgcgdPo billmsgcgdPo = new BillmsgcgdPo(cgdid, gysid, accid, account,"未审核");
        Integer addCgd = billmsgcgdService.addCgd(billmsgcgdPo);
        if (addCgd==1){
            return new ResponseResult<>(200,"已保存");
        }else {
            return new ResponseResult<>(9,"出错了");
        }
    }
    //出货单退货消息
    @PostMapping("/billrece/addBillreceReMsg")
    public ResponseResult<Void> getChdReMsg(@RequestParam("reid")Long reid,
                                            @RequestParam("chdid")Long chdid,
                                            @RequestParam("cstid")Long cstid,
                                            @RequestParam("accid")Long accid,
                                            @RequestParam("account")Double account){
        BillmsgchdrePo thd = billmsgchdreService.getThd(reid);
        if (thd!=null){
            return new ResponseResult<>(0, "重复发送");
        }
        BillmsgchdrePo BillmsgchdrePo = new BillmsgchdrePo(reid,chdid, cstid, accid, account,"未审核");
        Integer addThd = billmsgchdreService.addThd(BillmsgchdrePo);
        if (addThd==1){
            return new ResponseResult<>(200,"已保存");
        }else {
            return new ResponseResult<>(9,"出错了");
        }
    }
}
