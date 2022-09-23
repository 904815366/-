package com.example.repository.service;

import com.example.repository.repository.StockDetailRepository;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
@GlobalTransactional
public class StockDetailService {
    @Resource
    private StockDetailRepository stockDetailRepository;
    public void addShip(String id, Integer num, Long goodsid, String time) {
        stockDetailRepository.addShip(id,num,goodsid,time);
    }
}
