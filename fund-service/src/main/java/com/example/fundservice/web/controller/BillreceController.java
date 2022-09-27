package com.example.fundservice.web.controller;

import com.example.fundservice.dao.mysql.po.BillmsgcgdrePo;
import com.example.fundservice.dao.mysql.po.BillmsgchdrePo;
import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.service.BillmsgcgdreService;
import com.example.fundservice.service.BillmsgchdreService;
import com.example.fundservice.service.BillmsgchdService;
import com.example.fundservice.service.BillreceService;
import com.example.fundservice.util.ResponseResult;
import com.example.fundservice.web.controller.dto.BillreceDto;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BillreceController {
    @Resource
    private BillreceService billreceService;
    @Resource
    private BillmsgchdService billmsgchdService;
    @Resource
    private BillmsgcgdreService billmsgcgdreService;
    //收款单列表
    @GetMapping("/billrece/list")
    public ResponseResult<List<BillreceListDto>> BillreceList() {
        List<BillreceListDto> billreceListDtoList = billreceService.billreceList();
        return new ResponseResult(200, "OK", billreceListDtoList);
    }
    //新增收款单(编号sno/日期stime/出货单编号chdid
    //         客户cstid/结算账户accid/金额saccount
    //         制单人userid/描述sdecr/状态sstatus)
    @PostMapping("/billrece/addBillrece")
    public ResponseResult<Void> addBillrece(@RequestParam("chdid")Long chdid,
                                            @RequestParam("userid")Long userid,
                                            @RequestParam("sdecr")String fdecr){
        BillmsgchdPo chd = billmsgchdService.getChdByStatus(chdid);
        BillmsgcgdrePo cgdRe = billmsgcgdreService.getThdByStatus(chdid);
        if (chd==null){
            if (cgdRe==null){
                return new ResponseResult<Void>(0, "源单不存在" );
            }else {
                Long cstid = cgdRe.getGysid();
                Long accid = cgdRe.getAccid();
                Double account = cgdRe.getAccount();
                BillrecePo billrecePo = new BillrecePo();
                billrecePo.setChdid(chdid);
                billrecePo.setUserid(userid);
                billrecePo.setSdecr(fdecr);
                billrecePo.setSstatus("正常");
                billrecePo.setCstid(cstid);
                billrecePo.setAccid(accid);
                billrecePo.setSaccount(account);
                billrecePo.setType("采购退款");
                Integer add = billreceService.addBillrece(billrecePo);
                if (add==1){
                    billmsgcgdreService.delCgdRe(chdid);
                    //采购退款成功,调用api发消息给采购员,退款已收
                    billmsgcgdreService.cgdRe(billmsgcgdreService.getReid(chdid));
                    return new ResponseResult<Void>(200, "OK" );
                }else {
                    return new ResponseResult<Void>(1, "系统维护中" );
                }
            }

        }else {
            Long cstid = chd.getCstid();
            Long accid = chd.getAccid();
            Double account = chd.getAccount();

            BillrecePo billrecePo = new BillrecePo();
            billrecePo.setChdid(chdid);
            billrecePo.setUserid(userid);
            billrecePo.setSdecr(fdecr);
            billrecePo.setSstatus("正常");
            billrecePo.setCstid(cstid);
            billrecePo.setAccid(accid);
            billrecePo.setSaccount(account);
            billrecePo.setType("出货收款");

            Integer add = billreceService.addBillrece(billrecePo);
            if (add==1){
                billmsgchdService.delChd(chdid);
                //告诉出货员,收款已收
                billmsgchdService.updShip(chdid);
                return new ResponseResult<Void>(200, "OK" );
            }else {
                return new ResponseResult<Void>(1, "系统维护中" );
            }
        }
    }
    //删除收款单(批量)     snos
    @PostMapping("/billrece/delBillrece")
    public ResponseResult<Void> delBillrece(@RequestParam("snos")String snos){
        billreceService.delBillrece(snos);
        return new ResponseResult<Void>(200, "OK" );
    }
    //查看收款单     sno
    @PostMapping("/billrece/updBillrece")
    public ResponseResult<BillreceDto> updBillrece(@RequestParam("sno")Long sno){
        BillreceDto billreceDto = billreceService.getBillreceByStatus(sno);
        if (billreceDto==null){
            return new ResponseResult<BillreceDto>(0, "收款单不存在" ,null);
        }
        return new ResponseResult<BillreceDto>(200, "OK" ,billreceDto);
    }
    //出货单消息
    @PostMapping("/billrece/addBillreceMsg")
    public ResponseResult<Void> getChdMsg(@RequestParam("chdid")Long chdid,
                                          @RequestParam("cstid")Long cstid,
                                          @RequestParam("accid")Long accid,
                                          @RequestParam("account")Double account){
        BillmsgchdPo chd = billmsgchdService.getChd(chdid);
        if (chd!=null){
            return new ResponseResult<>(0, "重复发送");
        }
        BillmsgchdPo billmsgchdPo = new BillmsgchdPo(chdid, cstid, accid, account,"未审核");
        Integer addChd = billmsgchdService.addChd(billmsgchdPo);
        if (addChd==1){
            return new ResponseResult<>(200,"已保存");
        }else {
            return new ResponseResult<>(9,"出错了");
        }
    }
    //采购单退货消息
    @PostMapping("/billpay/addBillpayReMsg")
    public ResponseResult<Void> getCgdReMsg(@RequestParam("reid")Long reid,
                                            @RequestParam("cgdid")Long cgdid,
                                            @RequestParam("gysid")Long gysid,
                                            @RequestParam("accid")Long accid,
                                            @RequestParam("account")Double account){
        BillmsgcgdrePo thd = billmsgcgdreService.getThd(cgdid);
        if (thd!=null){
            return new ResponseResult<>(0, "重复发送");
        }
        BillmsgcgdrePo billmsgcgdrePo = new BillmsgcgdrePo(reid,cgdid, gysid, accid, account,"未审核");
        Integer addThd = billmsgcgdreService.addThd(billmsgcgdrePo);
        if (addThd==1){
            return new ResponseResult<>(200,"已保存");
        }else {
            return new ResponseResult<>(9,"出错了");
        }
    }
}
