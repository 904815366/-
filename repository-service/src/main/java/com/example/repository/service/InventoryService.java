package com.example.repository.service;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.po.GoodsPo;
import com.example.repository.dao.mysql.po.GoodsTypePo;
import com.example.repository.dao.mysql.po.InventoryEditPo;
import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.repository.InventoryRepository;
import com.example.repository.web.controller.dto.InventoryDto;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@GlobalTransactional
public class InventoryService {
    @Resource
    private InventoryRepository inventoryRepository;
    @Resource
    private GoodsTypeService goodsTypeService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private StockDetailService stockDetailService;

    public PageDto<InventoryDto> getInventoryList(Integer pageNum, Integer pageSize, Long typeid, String searchName) {
        GoodsTypePo type = new GoodsTypePo();
        if (!"".equals(typeid)&& typeid!=null){
            type = goodsTypeService.getTypeById(typeid);
        }
        searchName="".equals(searchName) ? null:searchName;
        Pageable pageable= PageRequest.of(pageNum, pageSize);
        GoodsPo goodsPo=GoodsPo.builder().goodsType(type).name(searchName).build();
        InventoryPo inventoryPo=InventoryPo.builder().goods(goodsPo).build();
        Example<InventoryPo> example=Example.of(inventoryPo);
        PageDto<InventoryDto> pageDto=inventoryRepository.findAll(example,pageable);
        return pageDto;
    }

    public void edit(List<InventoryEditPo> poList) {
            inventoryRepository.edit(poList);

    }

    public void addShip(String id, Integer num, Long goodsid, String time) {
        goodsService.releaseStock(goodsid, num);
        stockDetailService.addShip(id,num,goodsid,time);
    }
}
