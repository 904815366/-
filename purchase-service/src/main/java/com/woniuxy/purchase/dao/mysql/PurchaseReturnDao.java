package com.woniuxy.purchase.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.purchase.entity.dto.DetailsByGoodsDto;
import com.woniuxy.purchase.entity.dto.DetailsDto;
import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.dto.PurchaseReturnList;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.web.fo.PurchaseReturnListFo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PurchaseReturnDao extends BaseMapper<PurchaseReturnPo> {
    List<PurchaseReturnList> findAllPurchaseReturn(PurchaseReturnListFo fo);

    PurchaseReturnDto findPurchaseReturnById(Long id);

    Integer deleteById(Long id);

    Integer modifyStatus(@Param("id") Long id,@Param("status") Integer status);

    Integer modifyPaymentStatus(@Param("id") Long id,@Param("status") Integer status);

    List<DetailsDto> findReturn();

    List<DetailsByGoodsDto> findReturnDetailsByGoods();
}
