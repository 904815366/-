package com.example.auth.config.auth;

import com.example.auth.mysql.po.UserRolePo;
import com.example.auth.mysql.UserMysqlDao;
import com.example.auth.mysql.po.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMysqlDao userMysqlDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("查询数据库，根据用户名 {} 查询这个用户的密码和所具有的所有权限。", username);

        UserPo userPo = userMysqlDao.findByUsername(username).orElseThrow(() -> new NullPointerException("未查询到用户"));


        SimpleGrantedAuthority s;
        Set<String> roleNameSet = userPo.getRoleName();
        Set<String> PermissionNameSet = new HashSet<>();
        Set<UserRolePo> roleSet = userPo.getRoleSet();

        for (UserRolePo userRolePo : roleSet) {
            PermissionNameSet.addAll(userRolePo.getRolePermissionName());
        }

        String collect = Stream.concat(roleNameSet.stream(), PermissionNameSet.stream())
                .collect(Collectors.joining(","));

        return new User(userPo.getUsername(), userPo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(collect));

    }

}
