package com.example.repository.repository;

import com.example.repository.dao.mysql.GoodsDao;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.exception.NotEnoughStockException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class GoodsRepository {
    @Resource
    private GoodsDao goodsDao;

    public String releaseStock(Long id, Integer num) {
        GoodsPo goodsPo = goodsDao.findById(id).get();
        if (goodsPo.getStock()<num){
            throw new NotEnoughStockException();
        }else{
            goodsPo.setStock(goodsPo.getStock()-num);
            return "ok";
        }
    }

    public GoodsPo findGood(Long goodsId) {
        if (goodsId!=null && !goodsId.equals("")){
            GoodsPo goodsPo = goodsDao.findById(goodsId).get();
            return goodsPo;
        }
        return null;
    }
}
