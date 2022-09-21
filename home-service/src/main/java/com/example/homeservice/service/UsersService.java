package com.example.homeservice.service;

import com.example.homeservice.dao.mysql.po.RolePo;
import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.dao.redis.UsersRedis;
import com.example.homeservice.dao.redis.po.UsersRedisPo;
import com.example.homeservice.repository.UsersRepository;
<<<<<<< master
=======
import com.example.homeservice.utils.JwtUtils;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
import com.example.homeservice.utils.SnowflakeIdGenerator;
import com.example.homeservice.web.converter.UsersConverter;
import com.example.homeservice.web.fo.AddUsersFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UsersService {
    @Resource
    private UsersRepository usersRepository;
    @Resource
    private UsersRedis usersRedis;
    @Resource
<<<<<<< master
    private UsersConverter usersConverter;
=======
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UsersConverter usersConverter;
    @Resource
    private SignService signService;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
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

    public boolean modifyUser(UsersPo usersPo) {
<<<<<<< master
        boolean b = usersRepository.modifyUser(usersPo);
        if (b)
            usersRedis.delete(usersConverter.fromRedisPo(usersPo));
=======
        String username = queryNameById(usersPo.getId()).getUsername();
        System.out.println("修改前username:"+username);
        boolean b = usersRepository.modifyUser(usersPo);
        if (b) {
            stringRedisTemplate.delete(username);
            log.info("修改员工权限,redis中将该员工jwtToken删除");
            usersRedis.delete(usersConverter.fromRedisPo(usersPo));
        }
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
        return b;
    }

    public void modifyStatus(UsersPo po) {
        usersRepository.modifyStatus(po);
<<<<<<< master
        System.out.println("modifyStatus:" + po);
=======
        stringRedisTemplate.delete(po.getUsername());
        log.info("删除员工,redis中将该员工jwtToken删除");

>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
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
<<<<<<< master
=======
    }

    public UsersPo findById(Long id) {
        UsersRedisPo redisPo = usersRedis.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(redisPo)) {
            return usersConverter.from(redisPo);
        }
        UsersPo usersPo = usersRepository.findById(id).orElseThrow(() -> new NullPointerException("删除失败"));
        usersRedis.save(usersConverter.fromRedisPo(usersPo));
        return usersPo;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
    }
}
