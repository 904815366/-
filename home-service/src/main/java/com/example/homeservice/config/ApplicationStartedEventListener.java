package com.example.homeservice.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;



@Component
@Slf4j
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        log.info("项目启动,调用add_sign_delayed消息");
        rabbitTemplate.convertAndSend( "add_sign_delayed","add_sign_delayed");
    }
}


