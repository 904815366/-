package com.example.fundservice.web.controller;

import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.service.BillreceService;
import com.example.fundservice.util.ResponseResult;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BillreceController {
    @Resource
    private BillreceService billreceService;
    //收款单列表
    @GetMapping("/billrece/list")
    public ResponseResult BillreceList() {
        List<BillreceListDto> billreceListDtoList = billreceService.billreceList();
        return new ResponseResult(200, "OK", billreceListDtoList);
    }
    //新增收款单(编号sno/日期stime/出货单编号chdid
    //         客户cstid/结算账户accid/金额saccount
    //         制单人userid/描述sdecr/状态sstatus)
    @PostMapping("/billrece/addBillrece")
    public ResponseResult<Void> addBillrece(@RequestParam("chdid")Integer chdid,
                                            @RequestParam("userid")Integer userid,
                                            @RequestParam("sdecr")String fdecr,
                                            @RequestParam("sstatus")String fstatus){
        BillmsgchdPo chd = billreceService.getChdByStatus(chdid);
        if (chd==null){
            return new ResponseResult<Void>(0, "出货单不存在" );
        }else {
            Integer gysid = chd.getCstid();
            Integer accid = chd.getAccid();
            Double account = chd.getAccount();

            BillrecePo billrecePo = new BillrecePo();
            billrecePo.setChdid(chdid);
            billrecePo.setUserid(userid);
            billrecePo.setSdecr(fdecr);
            billrecePo.setSstatus(fstatus);
            billrecePo.setCstid(gysid);
            billrecePo.setAccid(accid);
            billrecePo.setSaccount(account);

            Integer add = billreceService.addBillrece(billrecePo);
            if (add==1){
                billreceService.updChd(chdid);
                return new ResponseResult<Void>(200, "OK" );
            }else {
                return new ResponseResult<Void>(00, "系统维护中" );
            }
        }
    }
    //删除收款单
    //修改收款单
    //出货单消息
    @PostMapping("/billpay/addBillreceMsg")
    public ResponseResult<Void> getChdMsg(@RequestParam("chdid")Integer chdid,
                                          @RequestParam("cstid")Integer cstid,
                                          @RequestParam("accid")Integer accid,
                                          @RequestParam("account")Double account){
        BillmsgchdPo chd = billreceService.getChd(chdid);
        if (chd!=null){
            return new ResponseResult<>(0, "重复发送");
        }
        BillmsgchdPo billmsgchdPo = new BillmsgchdPo(chdid, cstid, accid, account,"未审核");
        Integer addChd = billreceService.addChd(billmsgchdPo);
        if (addChd==1){
            return new ResponseResult<>(200,"已保存");
        }else {
            return new ResponseResult<>(00,"出错了");
        }
    }
}
