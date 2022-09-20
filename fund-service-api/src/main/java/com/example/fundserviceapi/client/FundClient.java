package com.example.fundserviceapi.client;

import com.example.fundserviceapi.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fund-service")
public interface FundClient {
    //采购单消息     出货单chdix/供应商gysid/结算账户accid/金额account
    @PostMapping("/billpay/addBillpayMsg")
    public ResponseResult<Void> getCgdMsg(@RequestParam("cgdid")Integer cgdid,
                                          @RequestParam("gysid")Integer gysid,
                                          @RequestParam("accid")Integer accid,
                                          @RequestParam("account")Double account);
    //出货单消息     采购单cgdix/客户cstid/结算账户accid/金额account
    @PostMapping("/billpay/addBillreceMsg")
    public ResponseResult<Void> getChdMsg(@RequestParam("chdid")Integer chdid,
                                          @RequestParam("cstid")Integer cstid,
                                          @RequestParam("accid")Integer accid,
                                          @RequestParam("account")Double account);
}
