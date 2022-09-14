package com.example.auth.config.auth;


import com.example.util.JwtUtils;
import com.example.util.JsonUtils;
import com.example.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Collection;
import java.util.Map;


@Slf4j
@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource private StringRedisTemplate template;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        log.info("用户 {} 登录成功", user.getUsername());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

//        saveAuthoritiesToRedis(user, authorities);

        respInfo(httpServletResponse, user);
    }

    private void respInfo(HttpServletResponse httpServletResponse, User user) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        PrintWriter out = httpServletResponse.getWriter();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        String strAuthorities = StringUtils.collectionToCommaDelimitedString(authorities);
        String jwtStr = JwtUtils.createJWT(user.getUsername(),strAuthorities);

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
