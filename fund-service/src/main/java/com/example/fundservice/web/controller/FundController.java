package com.example.fundservice.web.controller;
import com.example.fundservice.service.BillpayService;
import com.example.fundservice.service.BillreceService;
import com.example.fundservice.util.ResponseResult;
import com.example.fundservice.web.controller.dto.BillpayListDto;
import com.example.fundservice.web.controller.dto.BillreceListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class FundController {
    @Resource
    private BillpayService billpayService;
    @Resource
    private BillreceService billreceService;

    @GetMapping("/test")
    public String Test() {
        return "ok";
    }

    /**
     * billpay
     * @return
     */
    @GetMapping("/billpay/list")
    public ResponseResult BillpayList() {
        List<BillpayListDto> billpayListDtoList = billpayService.list();
        return new ResponseResult(200, "OK", billpayListDtoList);
    }





    /**
     * billrece
     * @return
     */
    @GetMapping("/billrece/list")
    public ResponseResult BillreceList() {
        List<BillreceListDto> billreceListDtoList = billreceService.list();
        return new ResponseResult(200, "OK", billreceListDtoList);
    }
}
