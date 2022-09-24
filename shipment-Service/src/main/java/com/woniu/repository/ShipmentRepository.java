package com.woniu.repository;

import com.example.fundserviceapi.client.FundClient;
import com.example.repository.api.client.RepositoryClient;
import com.example.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.MyException;
import com.woniu.dao.CusorderMapper;
import com.woniu.dao.ShipmentMapper;
import com.woniu.dao.po.Cusorder;
import com.woniu.dao.po.Shipment;
import com.woniu.repository.converter.ShipmentConverter;
import com.woniu.repository.dto.ShipmentDto;
import com.woniu.web.fo.AddShipmentFo;
import com.woniu.web.fo.ShpimentFo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private CusorderMapper cusorderMapper;

//    private

//    新增出货订单
    @GlobalTransactional
    @Transactional
    public void addShipment(AddShipmentFo addShipmentFo){
            //  通过转换器将fo转换成po
        Shipment po = shipmentConverter.from(addShipmentFo);
        Cusorder cusorder = cusorderMapper.selectById(po.getClorderId());
        shipmentMapper.insert(po);
        //  给资金发openfei                     出货单编号,客户id,结账账户id,本次收款
        fundClient.getChdMsg(po.getId(), cusorder.getCusId(), po.getUmoneyId(), po.getPayeemoney());
        //        生成出货单后修改订单的状态
        shipmentMapper.upCusorderStatus(po.getClorderId());
        //        给仓库减库存
        addShipmentFo.getGoodsFos().forEach(e->{
            ResponseResult<Void> msg = repositoryClient.addShip(e.getId(), e.getNum(),"CH-"+po.getId().toString(),po.getAttime().toString());
            int code = msg.getCode();
            if (code!=200){
                throw new MyException("库存不足");
            }
        });
    }

//    修改出货单的状态，是否交钱了
    public void upShoipnmentStatus(Long id,String status){
        shipmentMapper.upShipmentStatus(id,status);
    }

//    分页待条件查询出货列表
    public PageInfo<ShipmentDto> getShipments(ShpimentFo shpimentFo){
        PageHelper.startPage(shpimentFo.getPageNum(),shpimentFo.getPageSize());
        List<ShipmentDto> shipmentDtos = shipmentMapper.selShipments(shpimentFo);
        PageInfo<ShipmentDto> shipmentDtoPageInfo = new PageInfo<>(shipmentDtos);
        return shipmentDtoPageInfo;
    }

}