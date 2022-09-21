package com.example.homeservice.web.controller;

import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import com.example.homeservice.service.SettlementAccountService;
import com.example.homeservice.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SettlementAccountController {
    @Resource
    private SettlementAccountService settlementAccountService;
    /**
     * 根据ID获取用户姓名   给资产微服务调用
     * @param id
     * @return
     */
    @GetMapping("/settlement/{id}")
    public ResponseResult<String> queryAccountById(@PathVariable("id") Long id){
        log.info("SettlementAccountController : queryAccountById 方法");
        SettlementAccountPo po = settlementAccountService.queryNameById(id);
        return new ResponseResult<>(200,"success",po.getAccount());
    }
}
