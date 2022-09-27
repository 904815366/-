package com.woniuxy.purchaseserviceapi.client;

import com.example.util.ResponseResult;
import com.woniuxy.purchaseserviceapi.dto.DetailsByGoodsDto;
import com.woniuxy.purchaseserviceapi.dto.DetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("purchase-service")
public interface PurchaseClient {
    //付款单
    @PostMapping("/purchase/modify/payment")
    public ResponseResult<String> modifyPaymentStatus(@RequestParam("id") Long id,@RequestParam("paymentStatus") Integer paymentStatus);
    //收款单
    @PostMapping("/return/modify/inpay")
    public ResponseResult<String> modifyInPay(@RequestParam("id") Long id,@RequestParam("status") Integer status);
    //获取采购单编号
    @GetMapping("/purchase/invoiceNumber/{id}")
    public ResponseResult<String> getInvoiceNumber(@PathVariable("id") Long id);
    //采购明细报表
    @GetMapping("/purchase/details/list")
    public ResponseResult<List<DetailsDto>> getPurchase();
    //采购明细报表(按商品)
    @GetMapping("/purchase/details/goods/list")
    public ResponseResult<List<DetailsByGoodsDto>> getByGoodsDto();
}
