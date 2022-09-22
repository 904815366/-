package com.example.repository.subscriber;

import com.alibaba.fastjson.JSONObject;
import com.example.repository.dao.mysql.StockDetailDao;
import com.example.repository.dao.mysql.po.StockDetailPo;
import netscape.javascript.JSObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PaperCustomerApplicaion {
    @Resource
    public StockDetailDao stockDetailDao;
    @RabbitListener(queues = "add-storage")
    public void demo(String msg){
        System.out.println(msg);
        String jsonBody = msg.split(":", 2)[1];
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] goodStrings = jsonObject.get("goods").toString()
                .substring(1, jsonObject.get("goods").toString().length())
                .split(",");
        for (String goodString : goodStrings) {
            String[] split = goodString.split("-");
            StockDetailPo detailPo = StockDetailPo.builder()
                    .id(jsonObject.get("invoiceNumber").toString())
                    .goodsid(Long.parseLong(split[0]))
                    .num(Integer.parseInt(split[split.length]))
                    .type(0)
                    .status(0)
                    .time(LocalDateTime.parse(jsonObject.get("invoiceTime").toString()))
                    .build();
            stockDetailDao.save(detailPo);
        }

    }
}
