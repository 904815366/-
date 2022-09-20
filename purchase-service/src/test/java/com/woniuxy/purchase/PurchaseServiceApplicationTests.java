package com.woniuxy.purchase;

import com.example.fundserviceapi.client.FundClient;
import com.example.fundserviceapi.util.ResponseResult;
import com.woniuxy.purchase.dao.redis.PurchaseRedisDao;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class PurchaseServiceApplicationTests {

    @Resource
    private FundClient fundClient;
    @Resource
    private PurchaseRedisDao purchaseRedisDao;

    @Resource
    private UuidUtils utils;
    @Test
    void contextLoads() {
        PurchaseDetail purchaseDetail = purchaseRedisDao.findById(1L).get();
        System.out.println(purchaseDetail);
    }

    @Test
    void demo(){
        // 获取用户信息（这里使用模拟数据）
        String userInfo = "myuser";
        // 根据 Token 和与用户相关的信息到 Redis 验证是否存在对应的信息
        boolean result = utils.validToken("10717f3b-df58-4098-960d-1c24e198bd49", userInfo);
        System.out.println(result);
    }
    @Test
    void demo2(){
        ResponseResult<Void> cgdMsg = fundClient.getCgdMsg(6, 1, 1, 3.0);
        System.out.println(cgdMsg);
    }

}
