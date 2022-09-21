package com.example.homeservice.web.controller;

import com.example.homeservice.dao.mysql.SupplierDao;
import com.example.homeservice.dao.po.SupplierPo;
import com.example.homeservice.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


@RestController
@Slf4j
public class SupplierController {
    @Resource
    private SupplierDao supplierDao;

    /**
     * 根据ID获取用户姓名   给资产微服务调用
     * @param id
     * @return
     */
    @GetMapping("/supplier/{id}")
    public ResponseResult<String> queryNameById(@PathVariable("id") Long id){
        log.info("SupplierController : queryNameById 方法");
        SupplierPo po = supplierDao.findById(id).orElseThrow(() -> new RuntimeException("根据ID未查询到供应商名字"));
        return new ResponseResult<String>(200,"success",po.getName());
    }
}
