package com.example.auth.config.auth;


import com.example.auth.mysql.UrlAuthorityDao;
import com.example.auth.mysql.po.UrlAuthorityPo;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;


@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UrlAuthorityDao urlAuthorityDao;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        List<UrlAuthorityPo> all = urlAuthorityDao.findAll();
        for (UrlAuthorityPo url : all) {
            stringRedisTemplate.opsForValue().set(url.getMethod()+":"+url.getUri(),url.getAuthority());
        }
    }
}


