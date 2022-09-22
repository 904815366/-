package com.woniuxy.purchase.repository;

import com.woniuxy.purchase.dao.mysql.PurchaseReturnDao;
import com.woniuxy.purchase.dao.mysql.PurchaseReturnDetailsDao;
import com.woniuxy.purchase.dao.redis.PurchaseReturnRedisDao;
import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@RequiredArgsConstructor
@Transactional
public class PurchaseReturnRepository {
    private final PurchaseReturnDao purchaseReturnDao;
    private final PurchaseReturnRedisDao purchaseReturnRedisDao;
    private final PurchaseReturnDetailsDao purchaseReturnDetailsDao;

    public PurchaseReturnDto findById(Long id) {
        PurchaseReturnDto purchaseReturnDto = null;
        try {
            purchaseReturnDto = purchaseReturnRedisDao.findById(id).get();
            System.out.println("redis查的!");
        } catch (Exception e) {
            e.printStackTrace();
            purchaseReturnDto = purchaseReturnDao.findPurchaseReturnById(id);
            purchaseReturnRedisDao.save(purchaseReturnDto);
        }
        return purchaseReturnDto;
    }

    public boolean deleteById(Long[] ids) {
        for (Long id : ids) {
            purchaseReturnRedisDao.findById(id).ifPresent(new Consumer<PurchaseReturnDto>() {
                @Override
                public void accept(PurchaseReturnDto purchaseReturnDto) {
                    purchaseReturnRedisDao.deleteById(id);
                }
            });
            int row = purchaseReturnDao.deleteById(id);
            if (row == 0) {
                return false;
            } else {
                purchaseReturnDetailsDao.deleteByReturnId(id);
            }
        }
        return true;
    }

    public void addReturn(PurchaseReturnPo po, List<PurchaseReturnDetailsPo> detailsPoList) {
        int insert = purchaseReturnDao.insert(po);
        if (insert != 0) {
            for (PurchaseReturnDetailsPo detailsPo : detailsPoList) {
                detailsPo.setPurchaseReturnId(po.getId());
                purchaseReturnDetailsDao.insert(detailsPo);
            }
        }
    }

    public boolean modifyReturn(PurchaseReturnPo po, List<PurchaseReturnDetailsPo> detailsPoList){

        //删除缓存
        purchaseReturnRedisDao.findById(po.getId()).ifPresent(new Consumer<PurchaseReturnDto>() {
            @Override
            public void accept(PurchaseReturnDto purchaseReturnDto) {
                purchaseReturnRedisDao.deleteById(po.getId());
            }
        });
        PurchaseReturnPo returnPo = purchaseReturnDao.selectById(po.getId());
        //对比版本号
        if(returnPo.getVersion()==po.getVersion()){
            //版本号+1
            po.setVersion(po.getVersion()+1);
            //修改
            int id = purchaseReturnDao.updateById(po);
            if(id==0){
                return false;
            }
            for (PurchaseReturnDetailsPo detailsPo : detailsPoList) {
                purchaseReturnDetailsDao.deleteById(detailsPo.getId());
                purchaseReturnDetailsDao.insert(detailsPo);
            }
            return true;
        }
        return false;
    }

}
