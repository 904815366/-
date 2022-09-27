package com.example.homeservice.service;

import com.example.homeservice.dao.mysql.po.RolePo;
import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.dao.redis.UsersRedis;
import com.example.homeservice.dao.redis.po.UsersRedisPo;
import com.example.homeservice.repository.UsersRepository;
import com.example.homeservice.utils.SnowflakeIdGenerator;
import com.example.homeservice.web.converter.UsersConverter;
import com.example.homeservice.web.fo.AddUsersFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;


@Service
@Transactional
@Slf4j
public class UsersService {
    @Resource
    private UsersRepository usersRepository;
    @Autowired
    private UsersRedis usersRedis;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UsersConverter usersConverter;
    @Resource
    private SignService signService;

    private SnowflakeIdGenerator snowflakeIdGenerator;


    public UsersService() {
        this.snowflakeIdGenerator = new SnowflakeIdGenerator(1, 1);
    }

    public void modifyPassword(UsersPo usersPo) {
        usersRepository.modifyPassword(usersPo);
        stringRedisTemplate.delete(usersPo.getUsername());
        log.info("修改密码,redis中将该员工jwtToken删除");

    }


    public void addUser(AddUsersFo fo, HttpServletRequest request) throws IOException {
        UsersPo usersPo = UsersPo.builder().id(snowflakeIdGenerator.nextId())
                .username(fo.getUsername())
                .password(fo.getPassword())
                .name(fo.getName()).tel(fo.getTel()).email(fo.getEmail())
                .role(RolePo.builder().id(fo.getRoleid()).build())
                .status(fo.getStatus()).createtime(LocalDateTime.now()).avatar(fo.getAvatar()).version(1).build();

        usersRepository.addUser(usersPo);
        signService.addSign(usersPo.getUsername(),request);
    }

    public boolean modifyUser(AddUsersFo fo) {
        log.info("准备修改员工{}信息", fo.getId());
        log.info("修改前查询员工{}信息", fo.getId());
        String username = queryNameById(fo.getId()).getUsername();
        log.info("查询到员工{}信息", username);
        boolean b = usersRepository.modifyUser(fo);
        if (b) {
            log.info("修改员工{}信息成功", fo.getId());
            stringRedisTemplate.delete(username);
            log.info("修改员工权限,redis中将该员工jwtToken删除");
            usersRedis.deleteById(fo.getId());
        } else {
            log.info("修改员工{}信息失败", fo.getId());
        }
        return b;
    }

    public void modifyStatus(UsersPo po) {
        usersRepository.modifyStatus(po);
        stringRedisTemplate.delete(po.getUsername());
        log.info("删除员工,redis中将该员工jwtToken删除");
        usersRedis.delete(usersConverter.fromRedisPo(po));
    }

    public UsersPo queryNameById(Long id) {
        UsersRedisPo redisPo = usersRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)) {
            return usersConverter.from(redisPo);
        }
        UsersPo po = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("根据ID未查询到用户名字"));
        usersRedis.save(usersConverter.fromRedisPo(po));
        return po;
    }

    public UsersPo findById(Long id) {
        UsersRedisPo redisPo = usersRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)) {
            return usersConverter.from(redisPo);
        }
        UsersPo usersPo = usersRepository.findById(id).orElseThrow(() -> new NullPointerException("删除失败"));
        usersRedis.save(usersConverter.fromRedisPo(usersPo));
        return usersPo;
    }
}
