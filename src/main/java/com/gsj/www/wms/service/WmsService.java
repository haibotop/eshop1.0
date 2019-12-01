package com.gsj.www.wms.service;

import com.gsj.www.order.domain.OrderInfoDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;
import com.gsj.www.wms.domain.SaleDeliveryOrderDTO;

/**
 * WMS中心对外提供的接口
 */
public interface WmsService {

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
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO orderInfoDTO);

    /**
     * 通知WMS中心，“支付订单”事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderInfoDTO orderInfoDTO);

    /**
     * 通知WMS中心，“取消订单”事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(OrderInfoDTO orderInfoDTO);

}

