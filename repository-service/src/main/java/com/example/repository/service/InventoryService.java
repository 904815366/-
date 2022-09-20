package com.example.repository.service;

import com.example.repository.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class InventoryService {
    @Resource
    private InventoryRepository inventoryRepository;
}
