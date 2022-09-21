package com.woniu.repository;

import com.example.fundserviceapi.client.FundClient;
import com.example.repository.api.client.RepositoryClient;
import com.example.util.ResponseResult;
import com.woniu.MyException;
import com.woniu.dao.ShipmentMapper;
import com.woniu.dao.po.Shipment;
import com.woniu.repository.converter.ShipmentConverter;
import com.woniu.web.fo.AddShipmentFo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
public class ShipmentRepository {

    @Resource
    private ShipmentMapper shipmentMapper;

    @Resource//资金openfeign
    private FundClient fundClient;

    @Resource
    private ShipmentConverter shipmentConverter;

    @Resource//仓库的openfeign
    private RepositoryClient repositoryClient;

//    private

//    新增出货订单
    @GlobalTransactional
    @Transactional
    public void addShipment(AddShipmentFo addShipmentFo){
            //  通过转换器将fo转换成po
        Shipment po = shipmentConverter.from(addShipmentFo);
        shipmentMapper.insert(po);
        //  给资金发openfei                     订单编号,客户id,结账账户id,本次收款
        fundClient.getChdMsg(po.getId(), po.getClorderId(), po.getUmoneyId(), po.getPayeemoney());
        //        生成出货单后修改订单的状态
        shipmentMapper.upCusorderStatus(po.getClorderId());
        //        给仓库减库存
        addShipmentFo.getGoodsFos().forEach(e->{
            ResponseResult<Void> msg = repositoryClient.releaseStock(e.getId(), e.getNum());
            int code = msg.getCode();
            if (code!=200){
                throw new MyException("库存不足");
            }
        });
    }
}
