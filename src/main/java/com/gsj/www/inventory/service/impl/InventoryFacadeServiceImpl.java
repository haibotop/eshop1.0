package com.gsj.www.inventory.service.impl;

import com.gsj.www.inventory.service.InventoryFacadeService;
import com.gsj.www.order.domain.OrderOrderDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;

/**
 * 库存中心对外接口service组件
 */
public class InventoryFacadeServiceImpl implements InventoryFacadeService {
    /**
     * 通知库存中心，"采购入库完成"事件发生了
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"提交订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"支付订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"取消订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"完成退货入库"事件发生了
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        return true;
    }

    /**
     * 查询商品sku的库存
     * @param goodsSkuId 商品sku id
     * @return 商品sku的库存
     */
    @Override
    public Long getSaleStockQuantity(Long goodsSkuId) {
        return 1159L;
    }
}
