package com.woniu.config;

import com.woniu.dao.MessageMapper;
import com.woniu.dao.po.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class RabbitMqConfig {

    @Resource
    private MessageMapper messageMapper;

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            if ( ack ) {
                log.debug("消息成功投递至 RabbitMQ（ 的 Exchange ）");
                Long id = Long.parseLong(correlationData.getId());
                Message message = messageMapper.selectById(id);
                message.setRetryCount(message.getRetryCount()-1);
                messageMapper.updateById(message);
                // 未完待续 ...
            } else {
                log.debug("消息未能成功投递到 RabbitMQ（的 Exchange ）");
            }
        });
        return rabbitTemplate;
    }

//      声明队列
    @Bean
    public Queue ReturnSales(){
    return new Queue("ReturnSales", true, false, false);
    }

}
