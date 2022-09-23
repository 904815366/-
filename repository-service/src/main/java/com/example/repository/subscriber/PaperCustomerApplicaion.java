package com.example.repository.subscriber;

import com.alibaba.fastjson.JSONObject;
import com.example.repository.dao.mysql.StockDetailDao;
import com.example.repository.dao.mysql.po.StockDetailPo;
import com.woniuxy.purchaseserviceapi.client.MessageClient;
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
    public MessageClient messageClient;
    @Resource
    public StockDetailDao stockDetailDao;
    @RabbitListener(queues = "add-storage")
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
            System.out.println(split);
            StockDetailPo detailPo = StockDetailPo.builder()
                    .id(jsonObject.get("invoiceNumber").toString())
                    .goodsid(Long.parseLong(split[0]))
                    .num(Integer.parseInt(split[1]))
                    .type(0)
                    .status(0)
                    .time(LocalDateTime.parse(jsonObject.get("invoiceTime").toString(),df))
                    .build();
            stockDetailDao.save(detailPo);
            messageClient.modifyStatus(Long.parseLong(msg.split(":", 2)[0]),"C");
        }

    }
}
