package com.example.repository.repository;

import com.example.repository.dao.mysql.InventoryDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class InventoryRepository {
    @Resource
    private InventoryDao inventoryDao;
}
