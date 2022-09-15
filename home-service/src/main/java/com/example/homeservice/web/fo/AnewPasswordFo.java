package com.example.homeservice.web.fo;

import com.example.homeservice.dao.mysql.UsersDao;
import com.example.homeservice.dao.po.UsersPo;
import com.example.homeservice.service.UsersService;
import com.example.homeservice.utils.ApplicationContextHolder;
import com.example.homeservice.utils.ResponseResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

@Slf4j
@Data
public class AnewPasswordFo {

    private String username;
    private String oldPassword;
    private String newPassword;

    public ResponseResult<Void> exec() {
        log.info("进入anewPassword的exec方法");
        log.info(username);
        log.info(oldPassword);
        log.info(newPassword);
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        UsersService usersService = applicationContext.getBean(UsersService.class);
        UsersDao usersDao = applicationContext.getBean(UsersDao.class);
        UsersPo usersPo = usersDao.findByUsernameAndPassword(username, oldPassword)
                .orElse(null);
        if (ObjectUtils.isEmpty(usersPo)) {
            log.info("旧密码错误,未查询到usersPo");
            return new ResponseResult<Void>(400, "旧密码错误,修改密码失败.", null);
        }

        log.info("旧密码正确,开始修改为新密码");
        usersPo.setPassword(newPassword);
        usersService.modifyPassword(usersPo);

        log.info("密码修改成功");
        return new ResponseResult<Void>(200, "密码修改成功.", null);
    }
}
