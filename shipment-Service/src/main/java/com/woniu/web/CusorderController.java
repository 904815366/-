package com.woniu.web;

import com.example.util.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.woniu.anon.IdempotentToken;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.service.CusOrderDetailService;
import com.woniu.service.CusorderService;
import com.woniu.web.fo.AddCusAndDetailFo;
import com.woniu.web.fo.CusorderFo;
import com.woniu.web.fo.UpSiteFo;
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
    public ResponseResult<PageInfo<CusorderDto>> getAllPage(CusorderFo cusorderFo){
//        如果页码为空
        if (ObjectUtils.isEmpty(cusorderFo.getPageNum())){
            cusorderFo.setPageNum(1);
        }
//        如果页大小为空
        if (ObjectUtils.isEmpty(cusorderFo.getPageSize())){
            cusorderFo.setPageSize(5);
        }
        try {
            PageInfo<CusorderDto> cusorderDtoPageInfo = cusorderService.qurAllPageByFo(cusorderFo);
            return new ResponseResult<PageInfo<CusorderDto>>(200,"OK",cusorderDtoPageInfo);
        } catch (Exception e) {
            return new ResponseResult<PageInfo<CusorderDto>>(400,"ERRO",null);
        }
    }

//    新增订单项目和订单详情
    @PostMapping("/addCus")
    @IdempotentToken
    public ResponseResult<String> addCus(@RequestBody AddCusAndDetailFo addCusAndDetailFo){
        try {
            cusorderService.addCus(addCusAndDetailFo);
            return new ResponseResult<>(200,"OK","新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400,"ERRO","新增失败");
        }
    }

//    修改方法
    @PostMapping("/upCus")
    @IdempotentToken
    @Transactional
    public ResponseResult<String> setCusAndDetail(@RequestBody AddCusAndDetailFo addCusAndDetailFo){
//        已经生成出货单的不可做修改 跟前端约定  status=2

        try {
//        先做删除操作
            cusorderService.removeById(addCusAndDetailFo.getId());
            detailService.deltODetailByOid(addCusAndDetailFo.getId());
//        在做新增操作
            cusorderService.addCus(addCusAndDetailFo);
            return new ResponseResult<>(200,"OK","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400,"ERRO","修改失败");
        }
    }

    
//    删除方法
    @GetMapping("/deltCus")
    public ResponseResult<String> deltCusDetail(Long oid){
        try {
            cusorderService.removeById(oid);
            detailService.deltODetailByOid(oid);
            return new ResponseResult<>(200,"OK","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400,"ERRO","删除失败");
        }
    }
    
//修改收货地址的接口
    @PostMapping("/setSite")
    public ResponseResult<String> setSite(UpSiteFo upSiteFo){
        try {
            cusorderService.upSite(upSiteFo);
            return new ResponseResult<>(200,"OK","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400,"ERRO","修改失败");
        }
    }

}
