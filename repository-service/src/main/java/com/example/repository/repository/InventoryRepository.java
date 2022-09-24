package com.example.repository.repository;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.InventoryDao;
import com.example.repository.dao.mysql.po.*;
import com.example.repository.web.controller.dto.InventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class InventoryRepository {
    @Resource
    private InventoryDao inventoryDao;


    public PageDto<InventoryDto> findAll(Example<InventoryPo> example, Pageable pageable) {
        Page<InventoryPo> pagePo = inventoryDao.findAll(example, pageable);
        PageDto<InventoryDto> pageDto = new PageDto<>();
        List<InventoryPo> content = pagePo.getContent();

        List<InventoryDto> dtoList = new ArrayList<>();

        for (InventoryPo po : content) {
            InventoryDto inventoryDto = InventoryDto.builder()
                    .goodsid(po.getGoods().getId())
                    .goodsName(po.getGoods().getName())
                    .profitloss(po.getProfitloss())
                    .size(po.getGoods().getSize())
                    .remark(po.getRemark())
                    .id(po.getId())
                    .status(po.getStatus())
                    .stock(po.getGoods().getStock())
                    .stocktake(po.getStocktake())
                    .typeName(po.getGoods().getGoodsType().getName())
                    .unit(po.getGoods().getUnit())
                    .build();
            dtoList.add(inventoryDto);
        }
        PageDto<InventoryDto> pageDtoList = pageDto.getPageDto(pagePo, dtoList);
        return pageDtoList;
    }

    public void edit(List<InventoryEditPo> poList) {
        for (InventoryEditPo inventoryEditPo : poList) {
            InventoryPo inventoryPo = inventoryDao.findById(inventoryEditPo.getId()).get();
            if (inventoryEditPo.getStocktake()!=null && !"".equals(inventoryEditPo.getStocktake())){
                inventoryPo.setStocktake(inventoryEditPo.getStocktake());
                inventoryPo.setProfitloss(inventoryEditPo.getStocktake()-inventoryPo.getGoods().getStock());
            }
            if(inventoryEditPo.getRemark()!=null && !"".equals(inventoryEditPo.getRemark())){
                inventoryPo.setRemark(inventoryEditPo.getRemark());
            }
            inventoryDao.save(inventoryPo);
        }
    }
}
