package com.gsj.www.order.price;

import com.gsj.www.order.domain.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销活动处理结果
 * @author holy
 */
public class PromotionActivityResult {
    /**
     * 促销减免金额
     */
    private Double discountAmount = 0.0;
    private List<OrderItemDTO> orderItem = new ArrayList<>();

    public PromotionActivityResult(){

    }

    public PromotionActivityResult(Double discountAmount){
        this.discountAmount = discountAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<OrderItemDTO> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItemDTO> orderItem) {
        this.orderItem = orderItem;
    }
}
