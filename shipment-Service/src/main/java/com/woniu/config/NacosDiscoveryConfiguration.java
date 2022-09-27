/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.woniu.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.dao.MessageMapper;
import com.woniu.dao.po.Message;
import com.woniu.filter.IdempotentTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@EnableDiscoveryClient
@Configuration
@EnableWebMvc
@EnableFeignClients(basePackages = "com.example")
@EnableRabbit
@Slf4j
public class NacosDiscoveryConfiguration implements WebMvcConfigurer {

    @Resource
    private IdempotentTokenInterceptor idempotentTokenInterceptor;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(idempotentTokenInterceptor);
    }

    @Scheduled(fixedRate = 6000)
    public void handle() {
        log.debug("定时任务开始干活，发送消息...");

        // 查询待发送消息：status = '发送中' and retry_count > 0
        QueryWrapper<Message> qw = new QueryWrapper<Message>()
                .eq("status", "发送中")
                .gt("retry_count", 0);

        List<Message> messageList = messageMapper.selectList(qw);
        if (messageList.isEmpty()) {
            log.debug("没有待发送消息");
            return;
        }

        log.debug("有 {} 条消息等待发送", messageList.size());
        messageList.forEach(e->{
            CorrelationData data=new CorrelationData(e.getId().toString());
            if (ObjectUtils.isEmpty(e.getExchange())){
                rabbitTemplate.convertAndSend( e.getRoutingKey(),(Object) e.getContent(), data);
            }else {
                rabbitTemplate.convertAndSend(e.getExchange(),e.getRoutingKey(),e.getContent(),data);
            }
//      messageList.
        });
    }
}
