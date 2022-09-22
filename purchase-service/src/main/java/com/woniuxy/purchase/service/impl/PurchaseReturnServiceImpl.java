package com.woniuxy.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.dao.mysql.PurchaseReturnDao;
import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.dto.PurchaseReturnList;
import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.repository.PurchaseReturnRepository;
import com.woniuxy.purchase.service.PurchaseReturnService;
import com.woniuxy.purchase.web.fo.PurchaseReturnListFo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseReturnServiceImpl implements PurchaseReturnService {
    @Resource
    private PurchaseReturnDao purchaseReturnDao;
    @Resource
    private PurchaseReturnRepository purchaseReturnRepository;
    @Resource
    private RedissonClient redissonClient;
    @Override
    public PageInfo<PurchaseReturnList> getPurchaseRuturnList(PurchaseReturnListFo fo) {
        PageHelper.startPage(fo.getPageNum(), fo.getPageSzie());
        List<PurchaseReturnList> allPurchaseReturn = purchaseReturnDao.findAllPurchaseReturn(fo);
        PageInfo<PurchaseReturnList> pageInfo = new PageInfo<>(allPurchaseReturn);
        return pageInfo;
    }

    @Override
    public PurchaseReturnDto findGetById(Long id) {
        return purchaseReturnRepository.findById(id);
    }

    @Override
    public boolean deleteByIds(Long[] ids) {
        RLock client = this.redissonClient.getLock("deleteByIds");
        client.lock();
        boolean result = purchaseReturnRepository.deleteById(ids);
        client.unlock();
        return result;
    }

    @Override
    public void addReturn(PurchaseReturnPo po, List<PurchaseReturnDetailsPo> detailsPoList) {
        RLock client = this.redissonClient.getLock("addReturn");
        client.lock();
        purchaseReturnRepository.addReturn(po,detailsPoList);
        client.unlock();
    }

    @Override
    public boolean modifyReturn(PurchaseReturnPo po, List<PurchaseReturnDetailsPo> detailsPoList) {
        RLock client = this.redissonClient.getLock("modifyReturn");
        client.lock();
        boolean result=purchaseReturnRepository.modifyReturn(po,detailsPoList);
        client.unlock();
        return result;
    }
}
