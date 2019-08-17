package com.gsj.www.wms.service.impl;

import com.gsj.www.order.domain.OrderOrderDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;
import com.gsj.www.wms.domain.SaleDeliveryOrderDTO;
import com.gsj.www.wms.service.WmsService;
import org.springframework.stereotype.Service;

/**
 * WMS中心对外提供的接口
 * @author holy
 */
@Service
public class WmsServiceImpl implements WmsService {
    /**
     * 创建采购入库单
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return
     */
    @Override
    public Boolean createPurchaseInputOrder(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        return true;
    }

    /**
     * 创建销售出库单
     * @param saleDeliveryOrderDTO 销售出库单DTO
     * @return
     */
    @Override
    public Boolean createSaleDeliveryOrder(SaleDeliveryOrderDTO saleDeliveryOrderDTO) {
        return true;
    }

    /**
     * 创建退货入库单
     * @param returnGoodsInputOrder 退货入库单DTO
     * @return
     */
    @Override
    public Boolean createReturnGoodsInputOrder(ReturnGoodsInputOrderDTO returnGoodsInputOrder) {
        return true;
    }

    /**
     * 通知WMS中心，"提交订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知WMS中心，"支付订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean informPayOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知WMS中心，"取消订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean informCancelOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }
}
