package com.woniu.repository;

import com.example.fundserviceapi.client.FundClient;
import com.example.repository.api.client.RepositoryClient;
import com.woniu.dao.ShipmentMapper;
import com.woniu.dao.po.Shipment;
import com.woniu.repository.converter.ShipmentConverter;
import com.woniu.web.fo.AddShipmentFo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void addShipment(AddShipmentFo addShipmentFo){
//        通过转换器将fo转换成po
        Shipment po = shipmentConverter.from(addShipmentFo);
        shipmentMapper.insert(po);
//        给资金发openfei                           订单编号,客户id,结账账户id,本次收款
        fundClient.getChdMsg(po.getId(),po.getClorderId(),po.getUmoneyId(),po.getPayeemoney());
//        给仓库减库存
        addShipmentFo.getGoodsFos().forEach(e->{
            repositoryClient.releaseStock(e.getId(),e.getNum());
        });
    }
}
