package com.example.auth.config.auth;


import com.example.auth.mysql.LoginLogDao;
import com.example.auth.mysql.po.LoginlogPo;
import com.example.auth.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.apache.tomcat.util.http.RequestUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource private StringRedisTemplate template;
    @Resource private LoginLogDao loginLogDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        log.info("用户 {} 登录成功", user.getUsername());

        String ip = IpUtil.getIpAddress(httpServletRequest);
        log.info("请求方ip:{}",ip);
        String adder = IpUtil.getAdder(ip);
        log.info("请求方地址:{}",adder);
        String osAndBrowserInfo = IpUtil.getOsAndBrowserInfo(httpServletRequest);
        log.info("请求方操作系统和浏览器版本:{}",osAndBrowserInfo);
        String[] splitosAndBrowserInfo = osAndBrowserInfo.split("---");

        //雪花ID
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1, 1);
        long id = snowflakeIdGenerator.nextId();
        //新增登录日志
        loginLogDao.save(LoginlogPo.builder().id(id).logintime(new Timestamp(System.currentTimeMillis()))
                .ip(ip).adder(adder).systems(splitosAndBrowserInfo[0]).browser(splitosAndBrowserInfo[1])
                .username(user.getUsername()).build());
        log.info("loginLog 添加成功");



        respInfo(httpServletResponse, user);
    }

    private void respInfo(HttpServletResponse httpServletResponse, User user) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        PrintWriter out = httpServletResponse.getWriter();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        String strAuthorities = StringUtils.collectionToCommaDelimitedString(authorities);
        String jwtStr = JwtUtils.createJWT(user.getUsername(),strAuthorities);
        template.opsForValue().set(jwtStr,jwtStr,1, TimeUnit.HOURS);
        Map<String, String> map = MapUtils.of(
                "code", "10000",
                "msg", "success",
                "data", jwtStr);

        String s = JsonUtils.toJSONString(map);

        out.write(s);
    }

    private void saveAuthoritiesToRedis(User user, Collection<? extends GrantedAuthority> authorities) {
        StringBuilder builder = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            builder.append(authority+",");
        }
        String strAuthorities = builder.toString();
        strAuthorities = strAuthorities.substring(0, strAuthorities.length() - 1);

        log.info("权限集合:{}",strAuthorities);

        template.opsForValue().set(user.getUsername(), strAuthorities);
    }
}
