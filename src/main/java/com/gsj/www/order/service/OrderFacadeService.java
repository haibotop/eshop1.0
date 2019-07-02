package com.gsj.www.order.service;

import com.gsj.www.order.domain.OrderOrderDTO;

import java.util.List;

/**
 * 订单中心对外提供的接口
 */
public interface OrderFacadeService {

    /**
     * 通知订单中心，“商品完成发货”事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informGoodsDeliveryFinishedEvent(Long orderId);

    /**
     * 通知订单中心，“退货工单审核不通过”事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informReturnGoodsWorksheetRejectedEvent(Long orderId);

    /**
     * 通知订单中心，“退货工单审核通过”事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informReturnGoodsWorsheetApprovedEvent(Long orderId);

    /**
     * 通知订单中心，“确认收到退货商品”事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informReturnGoodsReceivedEvent(Long orderId);

    /**
     * 通知订单中心，“退货入库单审核通过”事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informReturnGoodsInputOrderApprovedEvent(Long orderId);

    /**
     * 通知订单中心，“完成退款”事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informRefundFinishedEvent(Long orderId);

    /**
     * 通知订单中心，"订单发表评论"事件发生了
     * @param orderId 订单id
     * @return 处理结果
     */
    Boolean informPublishCommentEvent(Long orderId);

    /**
     * 从订单中心获取，确认收货时间超过了七天，而且还没有发表评论的订单
     * @return 订单信息DTO集合
     */
    List<OrderOrderDTO> listNotPublishedCommentOrders();

    /**
     * 通知订单中心，"订单批量发表评论"事件发生了
     * @param orderIds 订单id集合
     * @return 处理结果
     */
    Boolean informBatchPublishCommentEvent(List<Long> orderIds);
}

