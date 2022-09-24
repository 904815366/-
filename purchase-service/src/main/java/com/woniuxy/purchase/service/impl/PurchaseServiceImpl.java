package com.woniuxy.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.dao.mysql.PurchaseDao;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.dto.PurchaseList;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import com.woniuxy.purchase.repository.PurchaseRepository;
import com.woniuxy.purchase.service.PurchaseService;
import com.woniuxy.purchase.utils.UuidUtils;
import com.woniuxy.purchase.web.fo.PurchaseListFo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseDao purchaseDao;

    @Resource
    private PurchaseRepository purchaseRepository;

    @Resource
    private UuidUtils utils;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public PageInfo<PurchaseList> getPurchaseList(PurchaseListFo fo) {
        PageHelper.startPage(fo.getPageNum(), fo.getPageSzie());
        List<PurchaseList> list = purchaseDao.findPracticalList(fo);
        PageInfo<PurchaseList> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PurchaseDetail findByPurchaseId(Long purchaseId) {
        return purchaseRepository.findByPurchaseId(purchaseId);
    }

    @Override
    public void modifyStatus(String[] ids, Integer status, String validToken) {
        RLock client = this.redissonClient.getLock("modifyStatus");
        client.lock();
        purchaseRepository.modifyStatus(ids, status);
        client.unlock();
    }

    @Override
    public boolean deleteById(Long id, String validToken) {
        RLock client = this.redissonClient.getLock("deleteById");
        client.lock();
        boolean result = purchaseRepository.deleteById(id);
        client.unlock();
        return result;
    }

    @Override
    public void addPurchase(PurchasePo po, List<PurchaseDetailsPo> detailsPoList) {
        purchaseRepository.addPurchase(po, detailsPoList);
    }

    @Override
    public void modifyById(PurchasePo po, List<PurchaseDetailsPo> detailsPoList) {
        RLock client = this.redissonClient.getLock("modifyById");
        client.lock();
        purchaseRepository.modifyPurchase(po, detailsPoList);
        client.unlock();
    }

    @Override
    public boolean modifyPaymentStatus(Long id, Integer paymentStatus) {
        RLock client = this.redissonClient.getLock("modifyPaymentStatus");
        client.lock();;
        boolean result = purchaseRepository.modifyPaymentStatus(id, paymentStatus);
        client.unlock();
        return result;
    }
}
