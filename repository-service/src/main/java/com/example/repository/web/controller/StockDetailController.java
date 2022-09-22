package com.example.repository.web.controller;

import com.example.repository.repository.StockDetailRepository;
import com.example.repository.service.StockDetailService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StockDetailController {
    @Resource
    private StockDetailService stockDetailService;
}
