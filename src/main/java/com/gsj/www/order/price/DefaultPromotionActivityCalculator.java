package com.gsj.www.order.price;

import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.promotion.domain.PromotionActivityDTO;

/**
 * 默认促销活动的计算组件
 * @author holy
 */
public class DefaultPromotionActivityCalculator implements PromotionActivityCalculator {
    /**
     * 计算促销活动减免的金额
     * @param item
     * @param promotionActivity
     * @return
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        return new PromotionActivityResult();
    }
}
