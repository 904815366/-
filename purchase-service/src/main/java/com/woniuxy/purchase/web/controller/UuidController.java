package com.woniuxy.purchase.web.controller;

import com.woniuxy.purchase.utils.UuidUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UuidController {
    @Resource
    private UuidUtils utils;
    /**
     * 获取 Token 接口
     *
     * @return Token 串
     */
    @GetMapping("/token")
    public String getToken() {
        // 获取用户信息（这里使用模拟数据）
        // 注：这里存储该内容只是举例，其作用为辅助验证，使其验证逻辑更安全，如这里存储用户信息，其目的为:
        // - 1)、使用"token"验证 Redis 中是否存在对应的 Key
        // - 2)、使用"用户信息"验证 Redis 的 Value 是否匹配。
        String userInfo = "myuser";
        // 获取 Token 字符串，并返回
        return utils.generateToken(userInfo);
    }
}
