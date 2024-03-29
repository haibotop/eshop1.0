package com.gsj.www.schedule.service;

import com.gsj.www.customer.domain.ReturnGoodsWorksheetDTO;
import com.gsj.www.order.domain.OrderInfoDTO;
import com.gsj.www.purchase.domain.PurchaseOrderDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;

/**
 * 调度中心对外提供的接口
 */
public interface ScheduleService {

    /**
     * 通知库存中心，“采购入库完成”事件发生了
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    Boolean informPurchaseInputFinished(
            PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 通知库存中心，“提交订单”事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO orderInfoDTO);

    /**
     * 通知库存中心，“支付订单”事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderInfoDTO orderInfoDTO);

    /**
     * 通知库存中心，“取消订单”事件发生了
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(OrderInfoDTO orderInfoDTO);

    /**
     * 通知库存中心，“完成退货入库”事件发生了
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    Boolean informReturnGoodsInputFinished(
            ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);

    /**
     * 调度采购入库
     * @param purchaseOrderDTO 采购单DTO
     * @return 处理结果
     */
    Boolean schedulePurchaseInput(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * 调度销售出库
     * @param orderInfoDTO 订单DTO
     * @return 处理结果
     */
    Boolean scheduleSaleDelivery(OrderInfoDTO orderInfoDTO);

    /**
     * 调度退货入库
     * @param orderInfoDTO 订单DTO
     * @param returnGoodsWorksheetDTO 退货工单DTO
     * @return 处理结果
     */
    Boolean scheduleReturnGoodsInput(OrderInfoDTO orderInfoDTO,
                                     ReturnGoodsWorksheetDTO returnGoodsWorksheetDTO);

}
