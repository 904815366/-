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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
@Slf4j
public class UsersService {
    @Resource
    private UsersRepository usersRepository;
    @Resource
    private UsersRedis usersRedis;
    @Resource
    private UsersConverter usersConverter;
    private SnowflakeIdGenerator snowflakeIdGenerator;


    public UsersService() {
        this.snowflakeIdGenerator = new SnowflakeIdGenerator(1, 1);
    }

    public void modifyPassword(UsersPo usersPo) {
        usersRepository.modifyPassword(usersPo);
    }


    public void addUser(AddUsersFo fo) {
        UsersPo usersPo = UsersPo.builder().id(snowflakeIdGenerator.nextId())
                .username(fo.getUsername())
                .password(fo.getPassword())
                .name(fo.getName()).tel(fo.getTel()).email(fo.getEmail())
                .role(RolePo.builder().id(fo.getRoleid()).build())
                .status(fo.getStatus()).createtime(new Date()).avatar(fo.getAvatar()).version(1).build();

        usersRepository.addUser(usersPo);
    }

    public boolean modifyUser(UsersPo usersPo) {
        boolean b = usersRepository.modifyUser(usersPo);
        if (b)
            usersRedis.delete(usersConverter.fromRedisPo(usersPo));
        return b;
    }

    public void modifyStatus(UsersPo po) {
        usersRepository.modifyStatus(po);
        System.out.println("modifyStatus:" + po);
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
}
