package com.example.homeservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Hello {

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable("id") Integer id) {
        log.info("我成功了!!!!!!!  ");
        return "ok";
    }
}
