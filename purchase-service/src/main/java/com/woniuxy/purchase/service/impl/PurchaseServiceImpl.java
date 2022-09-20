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
    public boolean modifyStatus(String[] ids, Integer status,String validToken) {
        // 获取用户信息（这里使用模拟数据）
        String userInfo = "myuser";
        // 根据 Token 和与用户相关的信息到 Redis 验证是否存在对应的信息
        boolean result = utils.validToken(validToken, userInfo);
        if(result){
            RLock client = this.redissonClient.getLock("redissonClient");
            client.lock();
            purchaseRepository.modifyStatus(ids, status);
            client.unlock();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id,String validToken) {
        // 获取用户信息（这里使用模拟数据）
        String userInfo = "myuser";
        // 根据 Token 和与用户相关的信息到 Redis 验证是否存在对应的信息
        boolean result = utils.validToken(validToken, userInfo);
        if (result){
            RLock client = this.redissonClient.getLock("redissonClient");
            client.lock();
            purchaseRepository.deleteById(id);
            client.unlock();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void addPurchase(PurchasePo po, List<PurchaseDetailsPo> detailsPoList) {
        purchaseRepository.addPurchase(po, detailsPoList);
    }

    @Override
    public void modifyById(PurchasePo po, List<PurchaseDetailsPo> detailsPoList) {
        RLock client = this.redissonClient.getLock("redissonClient");
        client.lock();
        purchaseRepository.modifyPurchase(po, detailsPoList);
        client.unlock();
    }
}
