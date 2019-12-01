package com.gsj.www.inventory.service.impl;

import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.inventory.service.InventoryService;
import com.gsj.www.inventory.updater.*;
import com.gsj.www.order.domain.OrderInfoDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;
import com.gsj.www.wms.domain.SubmitOrderStockUpdaterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库存中心对外接口service组件
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    /**
     * 采购入库库存更新命令工厂
     */
    @Autowired
    private PurchaseInputStockUpdaterFactory<PurchaseInputOrderDTO> purchaseInputStockUpdaterCommandFactory;
    /**
     * 退货入库库存更新命令工厂
     */
    @Autowired
    private ReturnGoodsInputStockUpdaterFactory<ReturnGoodsInputOrderDTO> returnGoodsInputStockUpdaterCommandFactory;
    /**
     * 提交订单库存更新组件工厂
     */
    @Autowired
    private SubmitOrderStockUpdaterFactory<OrderInfoDTO> submitOrderStockUpdaterFactory;
    /**
     * 支付订单库存更新组件工厂
     */
    @Autowired
    private PayOrderStockUpdaterFactory<OrderInfoDTO> payOrderStockUpdaterFactory;
    /**
     * 取消订单库存更新组件工厂
     */
    @Autowired
    private CancelOrderStockUpdateFactory<OrderInfoDTO> cancelOrderStockUpdateFactory;
    /**
     * 商品库存管理模块DAO组件
     */
    @Autowired
    private GoodsStockDAO goodsStockDAO;

    /**
     * 通知库存中心，"采购入库完成"事件发生了
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        try {
            GoodsStockUpdater goodsStockUpdaterCommand = purchaseInputStockUpdaterCommandFactory.create(purchaseInputOrderDTO);
            goodsStockUpdaterCommand.updateGoodsStock();
        }catch (Exception e){
            logger.error("error",e);
        }
        return true;
    }

    /**
     * 通知库存中心，"完成退货入库"事件发生了
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        try{
             GoodsStockUpdater goodsStockUpdaterCommand = returnGoodsInputStockUpdaterCommandFactory.create(returnGoodsInputOrderDTO);
            goodsStockUpdaterCommand.updateGoodsStock();
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，"提交订单"事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderInfoDTO orderInfoDTO) {
        try {
            GoodsStockUpdater goodsStockUpdaterCommand = submitOrderStockUpdaterFactory.create(orderInfoDTO);
            goodsStockUpdaterCommand.updateGoodsStock();
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，"支付订单"事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderInfoDTO orderInfoDTO) {
        try{
            GoodsStockUpdater goodsStockUpdaterCommand = submitOrderStockUpdaterFactory.create(orderInfoDTO);
            goodsStockUpdaterCommand.updateGoodsStock();
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 通知库存中心，"取消订单"事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(OrderInfoDTO orderInfoDTO) {
        try {
            GoodsStockUpdater goodsStockUpdaterCommand = submitOrderStockUpdaterFactory.create(orderInfoDTO);
            goodsStockUpdaterCommand.updateGoodsStock();
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }



    /**
     * 查询商品sku的库存
     * @param goodsSkuId 商品sku id
     * @return 商品sku的库存
     */
    @Override
    public Long getSaleStockQuantity(Long goodsSkuId) {
        try {
            GoodsStockDO goodsStockDO = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
            if(goodsStockDO == null){
                return 0L;
            }
             return goodsStockDO.getSaleStockQuantity();
        }catch (Exception e){
            logger.error("error",e);
        }
        return 1159L;
    }
}
