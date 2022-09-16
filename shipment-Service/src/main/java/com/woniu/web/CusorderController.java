package com.woniu.web;

import com.github.pagehelper.PageInfo;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.service.CusorderService;
import com.woniu.web.fo.CusorderFo;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CusorderController {

    @Resource
    private CusorderService cusorderService;

    @GetMapping("/getAllpage")//分页带条件查询
    public PageInfo<CusorderDto> getAllPage(@RequestBody CusorderFo cusorderFo){
//        如果页码为空
        if (ObjectUtils.isEmpty(cusorderFo.getPageNum())){
            cusorderFo.setPageNum(1);
        }
//        如果页大小为空
        if (ObjectUtils.isEmpty(cusorderFo.getPageSize())){
            cusorderFo.setPageSize(5);
        }
        PageInfo<CusorderDto> cusorderDtoPageInfo = cusorderService.qurAllPageByFo(cusorderFo);
        return cusorderDtoPageInfo;
    }
}
