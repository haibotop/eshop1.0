package com.gsj.www.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * 满减类型的促销活动价格计算组件
 * @author holy
 */
@Component
public class ReachDiscountPromotionActivityCalculator implements  PromotionActivityCalculator {
    /**
     * 计算促销活动的减免金额
     * @param item
     * @param promotionActivity
     * @return
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        Double totalAmount = item.getPurchaseQuantity() * item.getPurchasePrice();

        String rulesJson = promotionActivity.getRule();

        JSONArray rules = JSONArray.parseArray(rulesJson);

        for(int i = 0; i < rules.size(); i++){
            JSONObject rule = rules.getJSONObject(i);

            Double thresholdAmount = rule.getDouble("thresholdAmount");
            Double reduceAmount = rule.getDouble("reduceAmount");

            if(totalAmount > thresholdAmount){
                return new PromotionActivityResult(reduceAmount);
            }
        }

        return new PromotionActivityResult();
    }
}
