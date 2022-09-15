package com.example.fundservice.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class FundController {



    @GetMapping("/test")
    public String Test() {
        return "ok";
    }

}
