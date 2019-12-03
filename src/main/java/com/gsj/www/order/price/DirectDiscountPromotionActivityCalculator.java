package com.gsj.www.order.price;

import com.alibaba.fastjson.JSONObject;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * 单品促销活动的价格计算组件
 * @author holy
 */
@Component
public class DirectDiscountPromotionActivityCalculator implements PromotionActivityCalculator {
    /**
     * 计算促销活动的减免金额
     * @param item
     * @param promotionActivity
     * @return
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        Double totalAmount = item.getPurchaseQuantity() * item.getPurchasePrice();
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        Double discountRate = rule.getDouble("discountRate");
        return new PromotionActivityResult(totalAmount * (1.0 - discountRate));
    }
}
