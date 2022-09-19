package com.woniu.web;

import com.github.pagehelper.PageInfo;
import com.woniu.anon.IdempotentToken;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.service.CusOrderDetailService;
import com.woniu.service.CusorderService;
import com.woniu.web.fo.AddCusAndDetailFo;
import com.woniu.web.fo.CusorderFo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CusorderController {

    @Resource
    private CusorderService cusorderService;

    @Resource
    private CusOrderDetailService detailService;

    @GetMapping("/getAllpage")//分页带条件查询
    public PageInfo<CusorderDto> getAllPage(CusorderFo cusorderFo){
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

//    新增订单项目和订单详情
    @PostMapping("/addCus")
    @IdempotentToken
    public void addCus(@RequestBody AddCusAndDetailFo addCusAndDetailFo){
        cusorderService.addCus(addCusAndDetailFo);
    }

//    修改方法
    @PostMapping("/upCus")
    @IdempotentToken
    @Transactional
    public void setCusAndDetail(@RequestBody AddCusAndDetailFo addCusAndDetailFo){
//        先做删除操作
        cusorderService.removeById(addCusAndDetailFo.getId());
        detailService.deltODetailByOid(addCusAndDetailFo.getId());
//        在做新增操作
        cusorderService.addCus(addCusAndDetailFo);
    }

    
//    删除方法
    @GetMapping("/deltCus")
    public void deltCusDetail(Long oid){
        cusorderService.removeById(oid);
        detailService.deltODetailByOid(oid);
    }
    




}
