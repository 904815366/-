package com.woniuxy.purchase.repository;

import com.woniuxy.purchase.dao.mysql.PurchaseDao;
import com.woniuxy.purchase.dao.mysql.PurchaseDetailsDao;
import com.woniuxy.purchase.dao.redis.PurchaseRedisDao;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@Transactional
public class PurchaseRepository {
    private final PurchaseRedisDao purchaseRedisDao;
    private final PurchaseDao purchaseDao;
    private final PurchaseDetailsDao purchaseDetailsDao;

    public PurchaseRepository(PurchaseRedisDao purchaseRedisDao, PurchaseDao purchaseDao,PurchaseDetailsDao purchaseDetailsDao) {
        this.purchaseRedisDao = purchaseRedisDao;
        this.purchaseDao = purchaseDao;
        this.purchaseDetailsDao = purchaseDetailsDao;
    }

    public PurchaseDetail findByPurchaseId(Long purchaseId) {
        PurchaseDetail purchaseDetail = null;
        try {
            purchaseDetail = purchaseRedisDao.findById(purchaseId).orElseThrow(NullPointerException::new);
            System.out.println("从redis查的!");

        } catch (Exception e) {
            e.printStackTrace();
            purchaseDetail = purchaseDao.findPracticalById(purchaseId);
            System.out.println(purchaseDetail);
            purchaseRedisDao.save(purchaseDetail);
        }
        return purchaseDetail;
    }

    public void modifyStatus(String[] ids,Integer status){
        for (String id : ids) {
            Long pid = Long.parseLong(id);
            purchaseRedisDao.findById(pid).ifPresent(new Consumer<PurchaseDetail>() {
                @Override
                public void accept(PurchaseDetail purchaseDetail) {
                    purchaseRedisDao.deleteById(pid);
                }
            });
        }
        purchaseDao.ModifyById(ids, status);
    }

    public void deleteById(Long id){
        purchaseRedisDao.findById(id).ifPresent(new Consumer<PurchaseDetail>() {
            @Override
            public void accept(PurchaseDetail purchaseDetail) {
                purchaseRedisDao.deleteById(id);
            }
        });
        purchaseDao.deleteById(id);
        purchaseDetailsDao.deleteByPurchaseId(id);
    }

    public void addPurchase(PurchasePo po, List<PurchaseDetailsPo> detailsPo){
        purchaseDao.insert(po);
        for (int i = 0; i < detailsPo.size(); i++) {
            detailsPo.get(i).setPurchaseId(po.getId());
        }
        purchaseDetailsDao.purchaseDetailsAdd(detailsPo);
    }
}
