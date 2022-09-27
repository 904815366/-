package com.example.repository.subscriber;

import com.alibaba.fastjson.JSONObject;
import com.example.repository.dao.mysql.GoodsDao;
import com.example.repository.dao.mysql.StockDetailDao;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.StockDetailPo;
import com.example.repository.service.GoodsService;
import com.example.repository.service.StockService;
import com.rabbitmq.client.Channel;
import com.woniuxy.purchaseserviceapi.client.MessageClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PaperCustomerApplicaion {
    @Resource
    public MessageClient messageClient;
    @Resource
    public StockDetailDao stockDetailDao;
    @Resource
    public StockService stockService;
    @Resource
    public GoodsService goodsService;
    @Resource
    public GoodsDao goodsDao;
    @RabbitListener(queues = "add-storage")
    public void demo(String msg, Channel channel,
                     @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
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
            GoodsPo good = goodsService.findGood(Long.parseLong(split[0]));
            good.setStock(good.getStock()+Integer.parseInt(split[1]));
            goodsDao.save(good);
            System.out.println(good);
            StockDetailPo detailPo = StockDetailPo.builder()
                    .id(jsonObject.get("invoiceNumber").toString())
                    .goods(goodsService.findGood(Long.parseLong(split[0])))
                    .num(Integer.parseInt(split[1]))
                    .type(0)  //0是采购
                    .status(0)
                    .time(LocalDateTime.parse(jsonObject.get("invoiceTime").toString(),df))
                    .build();
            stockDetailDao.save(detailPo);
            stockService.addInnum(Long.parseLong(split[0]),(Integer.parseInt(split[1])));
            messageClient.modifyStatus(Long.parseLong(msg.split(":", 2)[0]),"C");
        }

    }
}
