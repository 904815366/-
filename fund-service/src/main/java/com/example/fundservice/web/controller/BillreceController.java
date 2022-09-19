package com.example.fundservice.web.controller;

import com.example.fundservice.dao.mysql.po.BillmsgchdPo;
import com.example.fundservice.dao.mysql.po.BillrecePo;
import com.example.fundservice.service.BillreceService;
import com.example.fundservice.util.ResponseResult;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class BillreceController {
    @Resource
    private BillreceService billreceService;
    //收款单列表
    @GetMapping("/billrece/list")
    public ResponseResult BillreceList() {
        List<BillreceListDto> billreceListDtoList = billreceService.list();
        return new ResponseResult(200, "OK", billreceListDtoList);
    }
    //新增收款单(编号sno/日期stime/出货单编号chdid
    //         客户cstid/结算账户accid/金额saccount
    //         制单人userid/描述sdecr/状态sstatus)
    @PostMapping("/billrece/addBillrece/{chdid}/{userid}/{fdecr}/{fstatus}")
    public ResponseResult<Void> addBillrece(@PathVariable("chdid")Integer chdid,
                                            @PathVariable("userid")Integer userid,
                                            @PathVariable("sdecr")String fdecr,
                                            @PathVariable("sstatus")String fstatus){
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

            Integer add = billreceService.add(billrecePo);
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
    @PostMapping("/billpay/addBillreceMsg/{chdid}/{cstid}/{accid}/{account}")
    public ResponseResult<Void> getChdMsg(@PathVariable("chdid")Integer chdid,
                                          @PathVariable("cstid")Integer cstid,
                                          @PathVariable("accid")Integer accid,
                                          @PathVariable("account")Double account){
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
