package com.example.fundservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping("/tmp")
    public String tmp(@RequestHeader("x-jwt-token") String jwtToken) {
        return "tmp";
    }
}
