package com.example.homeservice.service;

import com.example.homeservice.dao.po.RolePo;
import com.example.homeservice.dao.po.UsersPo;
import com.example.homeservice.repository.UsersRepository;
import com.example.homeservice.utils.SimpleFormatUtil;
import com.example.homeservice.utils.SnowflakeIdGenerator;
import com.example.homeservice.web.fo.AddUsersFo;
import lombok.extern.java.Log;
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
        return usersRepository.modifyUser(usersPo);
    }

    public void modifyStatus(UsersPo po) {
        usersRepository.modifyStatus(po);
    }
}
