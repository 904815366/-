package com.example.repository.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@GlobalTransactional
public class StockDetailService {
}
