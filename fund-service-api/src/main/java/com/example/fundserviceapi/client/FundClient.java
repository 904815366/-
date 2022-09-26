package com.example.fundserviceapi.client;

import com.example.fundserviceapi.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="fund-service",contextId = "fs01")
public interface FundClient {
    /**
     * 采购消息
     * @param cgdid     采购单id
     * @param gysid     供应商id
     * @param accid     结算账户id
     * @param account   采购金额
     * @return
     */
    @PostMapping("/billpay/addBillpayMsg")
    public ResponseResult<Void> getCgdMsg(@RequestParam("cgdid")Long cgdid,
                                          @RequestParam("gysid")Long gysid,
                                          @RequestParam("accid")Long accid,
                                          @RequestParam("account")Double account);
    /**
     * 采购退货消息
     * @param reid      采购退货单id
     * @param cgdid     采购单id
     * @param gysid     退回采购物品的供应商id
     * @param accid     结算账户id
     * @param account   退货金额
     * @return
     */
    @PostMapping("/billpay/addBillpayReMsg")
    public ResponseResult<Void> getCgdReMsg(@RequestParam("reid")Long reid,
                                            @RequestParam("cgdid")Long cgdid,
                                            @RequestParam("gysid")Long gysid,
                                            @RequestParam("accid")Long accid,
                                            @RequestParam("account")Double account);



    /**
     * 出货消息
     * @param chdid     出货单id
     * @param cstid     客户id
     * @param accid     结算账户id
     * @param account   退货金额
     * @return
     */
    @PostMapping("/billrece/addBillreceMsg")
    public ResponseResult<Void> getChdMsg(@RequestParam("chdid")Long chdid,
                                          @RequestParam("cstid")Long cstid,
                                          @RequestParam("accid")Long accid,
                                          @RequestParam("account")Double account);
    /**
     * 出货退货消息
     * @param reid      出货退货单id
     * @param chdid     出货单id
     * @param cstid     要退货的客户id
     * @param accid     结算账户id
     * @param account   退款金额
     * @return
     */
    @PostMapping("/billrece/addBillreceReMsg")
    public ResponseResult<Void> getChdReMsg(@RequestParam("reid")Long reid,
                                            @RequestParam("chdid")Long chdid,
                                            @RequestParam("cstid")Long cstid,
                                            @RequestParam("accid")Long accid,
                                            @RequestParam("account")Double account);
}
