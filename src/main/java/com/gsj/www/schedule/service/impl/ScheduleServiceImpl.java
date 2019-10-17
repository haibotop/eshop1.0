package com.gsj.www.schedule.service.impl;

import com.gsj.www.customer.domain.ReturnGoodsWorksheetDTO;
import com.gsj.www.order.domain.OrderOrderDTO;
import com.gsj.www.purchase.domain.PurchaseOrderDTO;
import com.gsj.www.purchase.domain.PurchaseOrderItemDTO;
import com.gsj.www.schedule.service.ScheduleService;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderItemDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;
import com.gsj.www.wms.service.WmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 调度中心对外接口service组件
 * @author holy
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    /**
     * WMS中心对外接口service组件
     */
    @Autowired
    private WmsService wmsService;

    /**
     * 通知库存中心，"采购入库完成"事件发生了
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return
     */
    @Override
    public Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"提交订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"支付订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean informPayOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"取消订单"事件发生了
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean informCancelOrderEvent(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 通知库存中心，"完成退货入库"事件发生了
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return
     */
    @Override
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        return true;
    }

    /**
     * 调度采购入库
     * @param purchaseOrderDTO 采购单DTO
     * @return
     */
    @Override
    public Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrder) {
        try {
            //将采购单的基本信息拷贝到采购入库单中去
            PurchaseInputOrderDTO purchaseInputOrder = purchaseOrder.clone(PurchaseInputOrderDTO.class);
            purchaseInputOrder.setId(null);
            purchaseInputOrder.setGmtCreate(null);
            purchaseInputOrder.setGmtModified(null);

            //将采购单条目拷贝到采购入库单条目中去
            List<PurchaseInputOrderItemDTO> purchaseInputOrderItems = new ArrayList<>();
            for (PurchaseOrderItemDTO purchaseOrderItem : purchaseOrder.getItems()) {
                PurchaseInputOrderItemDTO purchaseInputOrderItem = purchaseOrderItem.clone(PurchaseInputOrderItemDTO.class);
                purchaseInputOrderItem.setId(null);
                purchaseInputOrderItem.setGmtCreate(null);
                purchaseInputOrderItem.setGmtModified(null);

                purchaseInputOrderItems.add(purchaseInputOrderItem);
            }

            purchaseInputOrder.setPurchaseInputOrderItemDTOS(purchaseInputOrderItems);

            //调用wms中心但接口
            wmsService.createPurchaseInputOrder(purchaseInputOrder);
        }catch (Exception e) {
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 调度销售出库
     * @param orderOrderDTO 订单DTO
     * @return
     */
    @Override
    public Boolean scheduleSaleDelivery(OrderOrderDTO orderOrderDTO) {
        return true;
    }

    /**
     * 调度退货入库
     * @param orderOrderDTO 订单DTO
     * @param returnGoodsWorksheetDTO 退货工单DTO
     * @return
     */
    @Override
    public Boolean scheduleReturnGoodsInput(OrderOrderDTO orderOrderDTO, ReturnGoodsWorksheetDTO returnGoodsWorksheetDTO) {
        return true;
    }
}
