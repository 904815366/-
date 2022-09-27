package com.example.repository.subscriber;

import com.alibaba.fastjson.JSONObject;
import com.example.repository.dao.mysql.StockDetailDao;
import com.example.repository.dao.mysql.po.StockDetailPo;
import com.example.repository.service.GoodsService;
import com.example.repository.service.StockService;
import com.woniuxy.purchaseserviceapi.client.MessageClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class InvoiceCustomerApplication {
    @Resource
    public MessageClient messageClient;
    @Resource
    public StockDetailDao stockDetailDao;
    @Resource
    public StockService stockService;
    @Resource
    public GoodsService goodsService;
    @RabbitListener(queues = "ReturnSales")
    public void demo(String msg){
        System.out.println(msg);
        String jsonBody = msg.split(":", 2)[1];
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] goodStrings = jsonObject.get("goods").toString()
                .substring(1,jsonObject.get("goods").toString().length()-1)
                .replace(" ", "")
                .split(",");
        for (String goodString : goodStrings) {
            String[] split = goodString.replace(" ", "").split("-");

            StockDetailPo detailPo = StockDetailPo.builder()
                    .id(jsonObject.get("sid").toString())
                    .goods(goodsService.findGood(Long.parseLong(split[0])))
                    .num(Integer.parseInt(split[1]))
                    .type(0)  //0是采购
                    .status(0)
                    .time(LocalDateTime.parse(jsonObject.get("time").toString(),df))
                    .build();
            stockDetailDao.save(detailPo);
            stockService.addInnum(Long.parseLong(split[0]),(Integer.parseInt(split[1])));
        }

    }
}
