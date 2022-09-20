package com.woniuxy.purchase.dao.mysql;

import com.woniuxy.purchase.entity.po.MessagePo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessageDaoTest {
    @Resource
    private MessageDao messageDao;
    @Test
    void selectMessageAll() {
        List<MessagePo> messagePos = messageDao.selectMessageAll();
        for (MessagePo messagePo : messagePos) {
            System.out.println(messagePo);
        }
    }
}