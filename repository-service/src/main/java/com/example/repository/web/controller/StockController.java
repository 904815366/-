package com.example.repository.web.controller;

import com.example.repository.service.StockDetailService;
import com.example.repository.service.StockService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StockController {
    @Resource
    private StockService stockService;
}
