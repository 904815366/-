package com.example.auth.config.auth;

import com.example.auth.mysql.PermsDao;
import com.example.auth.mysql.UserDao;
import com.example.auth.mysql.po.UsersPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
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
    private UserDao userDao;

    @Resource
    private PermsDao permsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("查询数据库，根据用户名 {} 查询这个用户的密码和所具有的所有权限。", username);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true; //账号锁定
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        UsersPo userPo = userDao.findByUsername(username).orElse(null);
        if (Objects.isNull(userPo)){
            log.info("登录的用户名不存在");
            accountNonExpired = false;
            return new User("null", "null",
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
        }
        Set<String> roleNameSet = permsDao.findUriAllByRoleId(userPo.getRoleId());
        String collect = roleNameSet.stream().collect(Collectors.joining(","));

        return new User(userPo.getUsername(), userPo.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList(collect));

    }

}
