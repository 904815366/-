package com.example.repository.service;

import com.example.repository.dao.mysql.po.GoodsTypePo;
import com.example.repository.repository.GoodsTypeRepository;
import com.example.repository.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class GoodsTypeService {
    @Resource
    private GoodsTypeRepository goodsTypeRepository;

    public GoodsTypePo getTypeById(Long id){
        return goodsTypeRepository.getTypeById(id);
    }
}
