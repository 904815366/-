package com.woniu.repository;

import com.woniu.dao.CusorderMapper;
import com.woniu.dao.po.Cusorder;
import com.woniu.web.fo.CusorderFo;
import com.woniu.web.fo.UpSiteFo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CusorderRepository {

    @Resource
    private CusorderMapper cusorderMapper;
    

//    简单的查询方法
    public List<Cusorder> getPageAllByfo(CusorderFo cusorderFo){
        List<Cusorder> po = cusorderMapper.querypageByfo(cusorderFo);
        return po;
    }

//    新增订单方法
    public void AddCus(Cusorder cusorder) {
    cusorderMapper.insert(cusorder);
    }

//    修改地址操作
       public void upSite(UpSiteFo siteFo){
           cusorderMapper.upSite(siteFo);
       };

}
