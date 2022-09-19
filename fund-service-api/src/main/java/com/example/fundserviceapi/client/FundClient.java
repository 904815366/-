package com.example.fundserviceapi.client;

import com.example.fundserviceapi.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("fund-service")
public interface FundClient {
    //采购单消息
    @PostMapping("/billpay/addBillpayMsg/{cgdid}/{gysid}/{accid}/{account}")
    public ResponseResult<Void> getCgdMsg(@PathVariable("cgdid")Integer cgdid,
                                          @PathVariable("gysid")Integer gysid,
                                          @PathVariable("accid")Integer accid,
                                          @PathVariable("account")Double account);
    //出货单消息
    @PostMapping("/billpay/addBillreceMsg/{chdid}/{cstid}/{accid}/{accid}")
    public ResponseResult<Void> getChdMsg(@PathVariable("chdid")Integer chdid,
                                          @PathVariable("cstid")Integer cstid,
                                          @PathVariable("accid")Integer accid,
                                          @PathVariable("account")Double account);
}
