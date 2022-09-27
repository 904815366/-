package com.example.repository.service;

import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.repository.GoodsRepository;
import io.seata.spring.annotation.GlobalTransactional;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
@GlobalTransactional
public class GoodsService {
    @Resource
    private GoodsRepository goodsRepository;

    @Resource
    private RedissonClient redissonClient;


    public void releaseStock(Long goodsid, Integer num) {
        RLock client = this.redissonClient.getLock("releaseStock");
        client.lock();
        goodsRepository.releaseStock(goodsid,num);
        client.unlock();
    }

    public GoodsPo findGood(Long goodsId) {
        return goodsRepository.findGood(goodsId);
    }
}
