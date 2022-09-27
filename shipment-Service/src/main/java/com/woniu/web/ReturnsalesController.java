package com.woniu.web;

import com.example.fundserviceapi.client.FundClient;
import com.example.util.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.woniu.anon.IdempotentToken;
import com.woniu.dao.MessageMapper;
import com.woniu.dao.po.*;
import com.woniu.repository.converter.ReturnsalesConverter;
import com.woniu.repository.dto.ReturnsalesDto;
import com.woniu.service.CusOrderDetailService;
import com.woniu.service.CusorderService;
import com.woniu.service.ReturnsalesService;
import com.woniu.service.ShipmentService;
import com.woniu.web.fo.PageReturnSalesFo;
import com.woniu.web.fo.ReturnsalesFo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ReturnsalesController {

    @Resource
    private ReturnsalesService returnsalesService;

    @Resource
    private ShipmentService shipmentService;

    @Resource
    private CusOrderDetailService cusOrderDetailService;

    @Resource
    private CusorderService cusorderService;

    @Resource
    private ReturnsalesConverter returnsalesConverter;

    @Resource
    private FundClient fundClient;

    @Resource
    private MessageMapper messageMapper;

//    新增退货单
    @IdempotentToken
    @GlobalTransactional
    public ResponseResult<String> addReturnSales(ReturnsalesFo returnsalesFo){

//          新增退货单的要求
//            出货状态为不为2因为状态2属于发货中
        Shipment shipment = shipmentService.getById(returnsalesFo.getShipmentId());
        if(shipment.getStatus().equals("2")){
            return new ResponseResult<>(500, "ERR", "当前货物运输中不可申请退货，请送达后再申请");
        }
        //根据订单id查询订单
        Cusorder cusorder = cusorderService.getById(shipment.getClorderId());

//        获取前端传过来的退单详情列表
        List<CusOrderDetail> RetucusOrderDetails = returnsalesFo.getCusOrderDetails();
//        新增退货单
        Returnsales returnsales = returnsalesConverter.from(returnsalesFo);
        returnsalesService.save(returnsales);
        //            给资金发消息  退货单id 出货单id 客户id 账户id  退款金额
        try {
            fundClient.getChdReMsg(returnsales.getId(),returnsales.getShipmentId(),cusorder.getCusId(),returnsalesFo.getAccid(),returnsalesFo.getReturnMoney());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseResult<>(404, "ERRO", "账号异常，请稍后，或联系管理员");
        }
//        遍历订单详情进行修改
        for (int i=0;i<RetucusOrderDetails.size();i++){
            CusOrderDetail e = RetucusOrderDetails.get(i);
//            根据订单详情id查询原来的订单详情
            Long id = e.getId();
            CusOrderDetail cusOrderDetail = cusOrderDetailService.getById(id);

           cusOrderDetail.setNum(cusOrderDetail.getNum()-e.getNum());
           if (cusOrderDetail.getNum()<0){
               return new ResponseResult<>(500, "ERR", "退货数量不可低于订单数量");
           }else if(cusOrderDetail.getNum()==0){
               CusOrderDetail OrigcusOrderDetail = cusOrderDetailService.getById(id);
               OrigcusOrderDetail.setStatus("退货");
               cusOrderDetailService.updateById(OrigcusOrderDetail);
               Message message = new Message();
               message.setRoutingKey("ReturnSales");
               message.setStatus("发送中");
               String sid = e.getId().toString();
               String jsonStr="{\"sid\":\""+OrigcusOrderDetail.getId()+"\",\"time\":\""+cusorder.getOrdertime()+"\",\"goods\":["+OrigcusOrderDetail.getGoodsId()+"-"+OrigcusOrderDetail.getNum()+"]}";
               message.setContent(jsonStr);
               message.setRetryCount(5);   // 消息内容   商品Id  商品数量
               messageMapper.insert(message);
               continue;
           }
//           修改原来成功的订单详情的商品数量
            cusOrderDetailService.updateById(cusOrderDetail);
//            新增一个失败的订单详情
           e.setId(null);
           e.setStatus("退货");
            cusOrderDetailService.save(e);
//      给仓库发消息,创建消息列表，定时器会自动触发
            Message message = new Message();
            message.setRoutingKey("ReturnSales");
            message.setStatus("发送中");
            String sid = e.getId().toString();
            String jsonStr="{\"sid\":\""+sid+"\",\"time\":\""+cusorder.getOrdertime()+"\",\"goods\":["+e.getGoodsId()+"-"+e.getNum()+"]}";
            message.setContent(jsonStr);
            message.setRetryCount(5);   // 消息内容   商品Id  商品数量
            messageMapper.insert(message);
        };

        return new ResponseResult<>(200, "OK", "新增成功");
    }



    //    分页查询退货列表
    @GetMapping("/getReturnSaless")
    public ResponseResult<PageInfo<ReturnsalesDto>> getReturnSaless(PageReturnSalesFo pageReturnSalesFo){
        //        如果页码为空
        if (ObjectUtils.isEmpty(pageReturnSalesFo.getPageNum())) {
            pageReturnSalesFo.setPageNum(1);
        }
//        如果页大小为空
        if (ObjectUtils.isEmpty(pageReturnSalesFo.getPageSize())) {
            pageReturnSalesFo.setPageSize(5);
        }

        try {
            PageInfo<ReturnsalesDto> pageRetuensaless = returnsalesService.getPageRetuensaless(pageReturnSalesFo);
       return new ResponseResult<PageInfo<ReturnsalesDto>>(200, "OK", pageRetuensaless);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(500, "查询失败", null);
        }
    }

//    暴露给资金的接口让他修改退款状态
    @PostMapping("/setReturnSalesAsStatus")
    public ResponseResult<String> setReturnSalesAsStatus(@RequestParam("id") Long sid){
        Returnsales returnsales = new Returnsales();
        returnsales.setId(sid);
        returnsales.setStatus("已退款");
        returnsalesService.updateById(returnsales);
        return new ResponseResult<>(200, "OK", "修改成功");
    }
}