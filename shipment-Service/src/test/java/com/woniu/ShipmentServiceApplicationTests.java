package com.woniu;

import com.github.pagehelper.PageInfo;
import com.woniu.dao.ReturnsalesMapper;
import com.woniu.dao.po.Returnsales;
import com.woniu.repository.CusorderRepository;
import com.woniu.repository.dto.CusorderDto;
import com.woniu.service.CusorderService;
import com.woniu.web.fo.CusorderFo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShipmentServiceApplicationTests {

    @Resource
    private CusorderService cusorderService;
    
    @Autowired
    private CusorderRepository cusorderRepository;

    @Test
    void contextLoads() {
        CusorderFo cusorderFo = new CusorderFo();
        cusorderFo.setPageNum(1);
        cusorderFo.setPageSize(5);
        PageInfo<CusorderDto> cusorderDtoPageInfo = cusorderService.qurAllPageByFo(cusorderFo);
        System.out.println(cusorderDtoPageInfo);
    }

    @Test
    void dom2(){
        cusorderRepository.getPageAllByfo(null);
    }
    
    
    

}
