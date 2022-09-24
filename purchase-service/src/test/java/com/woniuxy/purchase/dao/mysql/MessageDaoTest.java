package com.woniuxy.purchase.dao.mysql;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageDaoTest {
    @Resource
    private MessageDao messageDao;
    @Test
    void selectMessageAll() {
//        List<MessagePo> messagePos = messageDao.selectMessageAll();
//        for (MessagePo messagePo : messagePos) {
//            System.out.println(messagePo);
//        }
    }
}