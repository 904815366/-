package com.example.repository.repository;

import com.example.repository.dao.mysql.GoodTypeDao;
import com.example.repository.dao.mysql.po.GoodsTypePo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class GoodsTypeRepository {
    @Resource
    private GoodTypeDao gooGoodTypeDao;
    public GoodsTypePo getTypeById(Long id) {
        GoodsTypePo goodsType = gooGoodTypeDao.findById(id).get();
        return goodsType;
    }
}
