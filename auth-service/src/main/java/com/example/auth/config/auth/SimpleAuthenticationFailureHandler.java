package com.example.auth.config.auth;

import com.example.util.JsonUtils;
import com.example.util.MapUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println(exception.getClass());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());  // 返回什么状态码取决于你的项目经理的要求：大概率是无论成功还是失败，无脑返回 200 ，再根据返回数据中的 code 来判断是真的成功还是失败。
        PrintWriter out = response.getWriter();

        Map<String, String> map = MapUtils.of("code", "10086", "msg", "failure");

        if (exception instanceof AccountExpiredException) {
            map.put("data", "账号已过期，请重新激活");
        } else if (exception instanceof CredentialsExpiredException) {
            map.put("data", "密码已过期，请重置密码");
        } else if (exception instanceof LockedException) {
            map.put("data", "账号被锁定，请联系管理员");
        } else if (exception instanceof BadCredentialsException) {
            map.put("data", "用户名密码错误");
        } else {
            map.put("data", "登陆失败，请重试");
        }

        String s = JsonUtils.toJSONString(map);

        out.write(s);
    }
}
