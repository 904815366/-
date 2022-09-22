package com.example.repository.service;

import com.example.repository.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class GoodsService {
    @Resource
    private GoodsRepository goodsRepository;


    public void releaseStock(Long id, Integer num) {
        goodsRepository.releaseStock(id,num);
    }
    public void getByTypeidAndName(){

    }
}
