package com.example.homeservice.repository;

import com.example.homeservice.dao.mysql.UsersDao;
import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.utils.SimpleFormatUtil;
import com.example.homeservice.web.fo.QueryUsersFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UsersRepository {
    @Resource
    private UsersDao usersDao;
    @Resource
    private JdbcTemplate jdbcTemplate;

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
//        Date date = SimpleFormatUtil.stringForDate(queryUsersFo.getCreatetime());

        po.setUsername((queryUsersFo.getUsername() == null || queryUsersFo.getUsername().equals("")) ? null : queryUsersFo.getUsername());
        po.setStatus((queryUsersFo.getStatus() == null || queryUsersFo.getStatus().equals("")) ? null : queryUsersFo.getStatus());
        po.setCreatetime((queryUsersFo.getCreatetime() == null || queryUsersFo.getCreatetime().equals("")) ? null : queryUsersFo.getCreatetime());
        System.out.println(po);

        Example<UsersPo> example = Example.of(po);
        Page<UsersPo> page = usersDao.findAll(example, pageable);
        return page;
    }

    public void addUser(UsersPo usersPo) {
        usersDao.save(usersPo);
    }

    public Optional<UsersPo> findByUsername(String username) {
        return usersDao.findByUsername(username);
    }

    public boolean modifyUser(UsersPo po) {

        String sql = "update users set username=?,password=?," +
                "name=?,tel=?,email=?,role_id=?,status=?,version=?,avatar=? where username=? and version=?";

        int update = jdbcTemplate.update(sql, po.getUsername(), po.getPassword(), po.getName(), po.getTel(),
                po.getEmail(), po.getRole().getId(), po.getStatus(), po.getVersion() + 1, po.getAvatar(),
                po.getUsername(), po.getVersion());
        return update == 1;
    }

    public Optional<UsersPo> findByIdAndStatusNot(Long id,String status) {
        return usersDao.findByIdAndStatusNot(id,status);
    }

    public void modifyStatus(UsersPo po) {
        usersDao.save(po);
    }

    public Optional<UsersPo> findById(Long id){
        return usersDao.findById(id);
    }
}
