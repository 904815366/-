package com.woniuxy.purchase.config;

import com.woniuxy.purchase.dao.mysql.MessageDao;
import com.woniuxy.purchase.entity.po.MessagePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Consumer;

@EnableRabbit
@Configuration
@Slf4j
public class RabbtiMqConfig {
    @Resource
    private MessageDao messageDao;

    @Bean
    @Transactional
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //System.out.println("无论是否存在程序始终会执行此处");
                String str = correlationData.getId();
                long id = Long.parseLong(str);
                MessagePo messagePo = messageDao.selectById(id);
                if (ObjectUtils.isEmpty(messagePo)){
                    //确认交换机收到后将状态改为B
                    messagePo.setStatus("B");
                    messageDao.insert(messagePo);

                    //每发一次消息次数减一
                    int retryCount = messagePo.getRetryCount();
                    messagePo.setRetryCount(retryCount - 1);
                    log.info("消息发送次数剩余:{}" + messagePo.getRetryCount());
                    messageDao.insert(messagePo);
                }


            }
        });
        return rabbitTemplate;
    }
}
