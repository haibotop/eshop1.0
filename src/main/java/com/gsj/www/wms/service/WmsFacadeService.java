package com.gsj.www.wms.service;


import com.gsj.www.order.dto.OrderDTO;
import com.gsj.www.wms.dto.PurchaseInputOrderDTO;
import com.gsj.www.wms.dto.ReturnGoodsInputOrderDTO;
import com.gsj.www.wms.dto.SaleDeliveryOrderDTO;

/**
 * WMS中心对外提供的接口
 */
public interface WmsFacadeService {

    /**
     * 创建采购入库单
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 创建销售出库单
     * @param saleDeliveryOrderDTO 销售出库单DTO
     * @return 处理结果
     */
    Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrderDTO);

    /**
     * 创建退货入库单
     * @param returnGoodsInputOrder 退货入库单DTO
     * @return 处理结果
     */
    Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrder);

    /**
     * 通知WMS中心，“提交订单”事件发生了
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderDTO orderDTO);

    /**
     * 通知WMS中心，“支付订单”事件发生了
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderDTO orderDTO);

    /**
     * 通知WMS中心，“取消订单”事件发生了
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(OrderDTO orderDTO);

}

