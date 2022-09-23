package com.example.fundserviceapi.client;

import com.example.fundserviceapi.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fund-service")
public interface FundClient {
    //采购单消息(付款) 采购单cgdid/供应商gysid/结算账户accid/金额account/账单类型type:采购付款或出货退货
    @PostMapping("/billpay/addBillpayMsg")
    public ResponseResult<Void> getCgdMsg(@RequestParam("cgdid")Long cgdid,
                                          @RequestParam("gysid")Long gysid,
                                          @RequestParam("accid")Long accid,
                                          @RequestParam("account")Double account,
                                          @RequestParam("type")String type);
    //出货单消息(收款) 出货单chdid/客户cstid/结算账户accid/金额account/账单类型type:出货收款或采购退货
    @PostMapping("/billrece/addBillreceMsg")
    public ResponseResult<Void> getChdMsg(@RequestParam("chdid")Long chdid,
                                          @RequestParam("cstid")Long cstid,
                                          @RequestParam("accid")Long accid,
                                          @RequestParam("account")Double account,
                                          @RequestParam("type")String type);

}
