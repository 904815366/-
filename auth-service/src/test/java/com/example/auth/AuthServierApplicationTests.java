package com.example.auth;


import com.example.auth.config.auth.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

@SpringBootTest(classes = AuthServiceApplication.class)
@Transactional
class AuthServierApplicationTests {
    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Test
    void contextLoads() {

        UserDetails userDetails = myUserDetailsService.loadUserByUsername("tommy");
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority);
        }
    }

}
