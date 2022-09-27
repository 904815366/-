package com.woniuxy.purchase.service.impl;

import com.example.fundserviceapi.client.FundClient;
import com.example.repository.api.client.RepositoryClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.purchase.dao.mysql.PurchaseDao;
import com.woniuxy.purchase.dao.mysql.PurchaseReturnDao;
import com.woniuxy.purchase.dao.mysql.PurchaseReturnDetailsDao;
import com.woniuxy.purchase.entity.dto.PurchaseReturnDto;
import com.woniuxy.purchase.entity.dto.PurchaseReturnList;
import com.woniuxy.purchase.entity.dto.ReturnGoods;
import com.woniuxy.purchase.entity.dto.UserDto;
import com.woniuxy.purchase.entity.po.PurchaseReturnDetailsPo;
import com.woniuxy.purchase.entity.po.PurchaseReturnPo;
import com.woniuxy.purchase.repository.PurchaseReturnRepository;
import com.woniuxy.purchase.service.PurchaseReturnService;
import com.woniuxy.purchase.web.fo.PurchaseReturnListFo;
import io.seata.spring.annotation.GlobalTransactional;
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
    private PurchaseReturnDetailsDao purchaseReturnDetailsDao;
    @Resource
    private PurchaseReturnRepository purchaseReturnRepository;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private FundClient fundClient;
    @Resource
    private RepositoryClient repositoryClient;
    @Resource
    private PurchaseDao purchaseDao;
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

    @Override
    @GlobalTransactional
    public void modifyReturnStatus(Long id, Integer status) {
        RLock client = this.redissonClient.getLock("modifyReturn");
        client.lock();
        boolean result = purchaseReturnRepository.modifyReturnStatus(id, status);
        if(result){
            PurchaseReturnDto po = purchaseReturnDao.findPurchaseReturnById(id);
            List<ReturnGoods> detailsByReturnId = purchaseReturnDetailsDao.findDetailsByReturnId(id);
            for (ReturnGoods goods : detailsByReturnId) {
                repositoryClient.addShip(goods.getGoodsId().longValue(),goods.getNum(),po.getInvoiceNumber(),po.getInvoiceTime(),0);
            }
            PurchaseReturnPo returnPo = purchaseReturnDao.selectById(id);
            List<ReturnGoods> details = purchaseReturnDetailsDao.findDetailsByReturnId(id);
            String purchaseNumber = details.get(0).getPurchaseNumber();
            UserDto number = purchaseDao.findByNumber(purchaseNumber);
            fundClient.getCgdReMsg(id, number.getId(),returnPo.getSupplierId(),returnPo.getSettlementAccountId(),returnPo.getRefundAmount().doubleValue());
        }
        client.unlock();
    }

    @Override
    public boolean modifyPaymentStatus(Long id,Integer status) {
        return purchaseReturnRepository.modifyPaymentStatus(id,status);
    }
}
