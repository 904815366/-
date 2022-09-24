package com.woniuxy.purchase.timingtask;

import com.woniuxy.purchase.dao.mysql.MessageDao;
import com.woniuxy.purchase.entity.po.MessagePo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RegularlySend {
    @Resource
    private MessageDao messageDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(initialDelay=1000, fixedRate=6000)
    private void process() {
        List<MessagePo> messagePos = messageDao.selectMessageAll();
        if(messagePos.size()==0){
            System.out.println("休息下,等下再执行");
            return;
        }
        for (MessagePo messagePo : messagePos) {
            long id = messagePo.getId();
            String exchange = messagePo.getExchange();
            String content = messagePo.getContent();
            String routingKey = messagePo.getRoutingKey();
            int retryCount = messagePo.getRetryCount();
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(Long.toString(id));
            content=id+":"+content;
            if(StringUtils.isEmpty(exchange)){
                rabbitTemplate.convertAndSend(routingKey,(Object)content,correlationData);
            }else {
                rabbitTemplate.convertAndSend(exchange, routingKey, content,correlationData);
            }
        }
    }
}
