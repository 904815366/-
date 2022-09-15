package com.example.homeservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable("id") Integer id) {
        return "来啦" + id;
    }
}
