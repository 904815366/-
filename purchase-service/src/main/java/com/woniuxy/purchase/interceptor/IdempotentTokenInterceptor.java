package com.woniuxy.purchase.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.util.ResponseResult;
import com.woniuxy.purchase.annotation.IdempotentToken;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.utils.SnowflakeIdGenerator;
import com.woniuxy.purchase.utils.UuidUtils;
import com.woniuxy.purchase.web.converter.PurchaseDetailFoConverter;
import com.woniuxy.purchase.web.converter.PurchaseFoConverter;
import com.woniuxy.purchase.web.fo.PurchaseDetailFo;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能:不可重复验证
 * 在方法上@IdempotentToken
 */
@Component
@Slf4j
public class IdempotentTokenInterceptor  implements HandlerInterceptor {
    @Resource
    private UuidUtils utils;
    @Autowired
    private StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        System.out.println("进入IdempotentTokenInterceptor");

        // 当前请求要访问的不是 Controller 中的 "方法"，放行。
        if (!(object instanceof HandlerMethod))
            return true;

        // 1. 判断当前请求所要访问的那个方法的脑袋上面有没有 @IdempotentToken 注解
        //    如果没有，放行
        HandlerMethod handle = (HandlerMethod) object;
        Method method = handle.getMethod();

        IdempotentToken annotation = method.getAnnotation(IdempotentToken.class);
        if (annotation == null)
            return true;

        System.out.println(method.getName());
        // 2. 如果有。从请求头中获取幂等token
        //     如果请求头中没有，拒绝访问。
        // 从request请求体中拿到body中的JSON对象参数
        RequestReaderHttpServletRequestWrapper requestWrapper = new RequestReaderHttpServletRequestWrapper(request);
        String jsonBody = requestWrapper.getBody();
        JSONObject jsonObject = JSON.parseObject(jsonBody);
        String validToken = jsonObject.get("validToken").toString();
        // 获取用户信息（这里使用模拟数据）
        String userInfo = "myuser";
        // 根据 Token 和与用户相关的信息到 Redis 验证是否存在对应的信息
        boolean result = utils.validToken(validToken, userInfo);
        if(result){
            //放行
            return true;
        }else {
            //返回验证失败信息
            OutputStream outputStream = response.getOutputStream();// 获取输出流
            // 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
            response.setHeader("content-type", "application/json");
            response.setStatus(500);
            // 将字符转换成字节数组，指定以UTF-8编码进行转换
            String data="{\"code\":500,\"msg\":\"erro\",\"data\":\"表单重复提交\"}";
            byte[] dataByteArr = data.getBytes("UTF-8");
            //使用OutputStream流向客户端输出字节数组
            outputStream.write(dataByteArr);
            outputStream.flush();
            outputStream.close();
            return false;
        }
    }
}
