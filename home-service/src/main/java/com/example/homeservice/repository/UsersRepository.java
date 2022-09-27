package com.example.homeservice.repository;

import com.example.homeservice.dao.mysql.UsersDao;
import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.utils.SimpleFormatUtil;
import com.example.homeservice.web.fo.AddUsersFo;
import com.example.homeservice.web.fo.QueryUsersFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UsersRepository {
    @Resource
    private UsersDao usersDao;

    public void modifyPassword(UsersPo usersPo) {
        usersDao.save(usersPo);
    }

    public List<UsersPo> findUsersByUsernameAndStatus(String username_admin, String status) {
        return usersDao.findAllByUsernameNotAndStatusEquals(username_admin, status);
    }

    public Page<UsersPo> findUsersByUsernameAndStatusAndCreatetime(QueryUsersFo queryUsersFo) {
        Integer num = queryUsersFo.getNum() == null ? 0 : queryUsersFo.getNum();
        Integer size = queryUsersFo.getSize() == null ? 5 : queryUsersFo.getSize();
        Pageable pageable = PageRequest.of(num, size);

        UsersPo po = new UsersPo();

        po.setUsername((queryUsersFo.getUsername() == null || queryUsersFo.getUsername().equals("")) ? null : queryUsersFo.getUsername());
        po.setStatus((queryUsersFo.getStatus() == null || queryUsersFo.getStatus().equals("")) ? null : queryUsersFo.getStatus());
//        LocalDateTime createtime = queryUsersFo.getCreatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Instant instant = queryUsersFo.getCreatetime().toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);


        log.info("createtime:" + localDateTime);

        po.setCreatetime((queryUsersFo.getCreatetime() == null || queryUsersFo.getCreatetime().equals("")) ? null : localDateTime);
        System.out.println(po);

        Example<UsersPo> example = Example.of(po);
        return usersDao.findAll(example, pageable);

    }

    public void addUser(UsersPo usersPo) {
        usersDao.save(usersPo);
    }

    public Optional<UsersPo> findByUsername(String username) {
        return usersDao.findByUsername(username);
    }

    public boolean modifyUser(AddUsersFo fo) {
        log.info("here 4.");

        int update = usersDao.modifyUsers(fo.getUsername(), fo.getPassword(), fo.getName(), fo.getTel(),
                fo.getEmail(), fo.getRoleid(), fo.getStatus(), fo.getAvatar(),
                fo.getId(), fo.getVersion());

        log.info("修改语句执行成功数量:{}",update);


        return update == 1;
    }

    public Optional<UsersPo> findByIdAndStatusNot(Long id, String status) {
        return usersDao.findByIdAndStatusNot(id, status);
    }

    public void modifyStatus(UsersPo po) {
        usersDao.save(po);
    }

    public Optional<UsersPo> findById(Long id) {
        return usersDao.findById(id);
    }
}
