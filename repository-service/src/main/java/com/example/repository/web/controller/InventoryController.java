package com.example.repository.web.controller;

import com.example.repository.repository.InventoryRepository;
import com.example.repository.service.InventoryService;
import com.example.repository.util.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InventoryController {
    @Resource
    private InventoryRepository inventoryRepository;

    @Resource
    private InventoryService inventoryService;


}
