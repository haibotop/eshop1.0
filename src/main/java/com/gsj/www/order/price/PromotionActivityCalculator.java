package com.gsj.www.order.price;

import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.promotion.domain.PromotionActivityDTO;

/**
 * 促销活动处理组件接口
 * @author holy
 */
public interface PromotionActivityCalculator {
    /**
     * 处理促销活动
     * @return
     */
    PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity);
}
