package com.example.auth.config;

import com.example.auth.config.auth.SimpleAuthenticationSuccessHandler;
import com.example.auth.config.auth.MyUserDetailsService;
import com.example.auth.config.auth.SimpleAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity  // 启用 spring-security 功能。
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource private SimpleAuthenticationFailureHandler simpleAuthenticationFailureHandler;
    @Resource private SimpleAuthenticationSuccessHandler simpleAuthenticationSuccessHandler;
    @Resource private MyUserDetailsService myUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 它会影响到 spring-security 过滤器链。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(simpleAuthenticationSuccessHandler)
                .failureHandler(simpleAuthenticationFailureHandler)
        ;
        http.authorizeRequests()
                .anyRequest()
                .authenticated(); // 2


        http.httpBasic().disable(); //
        http.csrf().disable();  // 3
    }

}
