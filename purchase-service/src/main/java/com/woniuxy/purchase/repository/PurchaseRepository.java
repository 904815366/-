package com.woniuxy.purchase.repository;

import com.example.fundserviceapi.client.FundClient;
import com.example.fundserviceapi.util.ResponseResult;
import com.woniuxy.purchase.dao.mysql.MessageDao;
import com.woniuxy.purchase.dao.mysql.PurchaseDao;
import com.woniuxy.purchase.dao.mysql.PurchaseDetailsDao;
import com.woniuxy.purchase.dao.redis.PurchaseRedisDao;
import com.woniuxy.purchase.entity.dto.Goods;
import com.woniuxy.purchase.entity.dto.PurchaseDetail;
import com.woniuxy.purchase.entity.po.MessagePo;
import com.woniuxy.purchase.entity.po.PurchaseDetailsPo;
import com.woniuxy.purchase.entity.po.PurchasePo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class PurchaseRepository {
    private final FundClient fundClient;
    private final MessageDao messageDao;
    private final PurchaseRedisDao purchaseRedisDao;
    private final PurchaseDao purchaseDao;
    private final PurchaseDetailsDao purchaseDetailsDao;

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
        if(status==1){
            for (int i = ids.length - 1; i >= 0; i--) {
                PurchasePo purchasePo = purchaseDao.selectById(ids[i]);
                purchasePo.getInvoiceTime();
                List<PurchaseDetail> detail = purchaseDetailsDao.findPurchaseDetail(Integer.parseInt(ids[i]));
                //发openfeign生成付款单
                int suppilerId = purchasePo.getSupplierId().intValue();
                int settlementAccountId = purchasePo.getSettlementAccountId().intValue();
                double laterMoney = purchasePo.getLaterMoney().doubleValue();
                int id = purchasePo.getId().intValue();
                ResponseResult<Void> cgdMsg = fundClient.getCgdMsg(id, suppilerId, settlementAccountId, laterMoney);
                if(cgdMsg.getCode()==200){
                    //发消息给仓库入库
                    PurchaseDetail practicalById = purchaseDao.findPracticalById(Long.parseLong(ids[i]));
                    String invoiceNumber = practicalById.getInvoiceNumber();
                    String invoiceTime = practicalById.getInvoiceTime();
                    List<Integer> collect = practicalById.getGoods().stream().map(Goods::getId).collect(Collectors.toList());
                    String json="{\"invoiceNumber\":\""+invoiceNumber+"\",\"invoiceTime\":\""+invoiceTime+"\",\"goodsId\":\""+collect+"\"}";
                    MessagePo messagePo = new MessagePo(null, "", "add-storage",json , 5, "A");
                    messageDao.insert(messagePo);
                }
            }
        }
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

    public void modifyPurchase(PurchasePo po, List<PurchaseDetailsPo> detailsPo){
        System.out.println(po);
        PurchasePo purchasePo = purchaseDao.selectById(po.getId());
        System.out.println(purchasePo);
        //对比入参版本号和出参版本号是否一致
        if(po.getVersion()==purchasePo.getVersion()){
            //修改版本号
            po.setVersion(po.getVersion()+1);
            //修改内容
            purchaseDao.updateById(po);
            //修改订单详情
            for (PurchaseDetailsPo purchaseDetailsPo : detailsPo) {
                //清除原来的采购单详情
                purchaseDetailsDao.deleteByPurchaseId(po.getId());
                //重新添加新的采购单详情
                purchaseDetailsDao.insert(purchaseDetailsPo);
            }
        }
    }
}
