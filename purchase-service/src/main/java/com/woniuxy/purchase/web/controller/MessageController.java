package com.woniuxy.purchase.web.controller;

import com.example.util.ResponseResult;
import com.woniuxy.purchase.dao.mysql.MessageDao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {
    @Resource
    private MessageDao messageDao;

    @PostMapping("/message/modify")
    public ResponseResult<String> modifyStatus(Long id,String status){
        Integer integer = messageDao.modifyByIdStatus(id, status);
        if (integer==1){
            return new ResponseResult<>(200,"ok","修改成功!");
        }
        return new ResponseResult<>(500,"erro","修改失败!");
    }
}
