package com.gsj.www.order.price;

import com.gsj.www.promotion.constant.PromotionActivityType;
import com.gsj.www.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 折扣减免型的订单价格计算组件工厂
 * @author holy
 */
@Component
public class DiscountOrderPriceCalculatorFactory implements OrderPriceCalculatorFactory{
    /**
     * 默认的总金额计算组件
     */
    @Autowired
    private DefaultTotalPriceCalculator totalPriceCalculator;
    /**
     * 满减类型的促销活动计算组件
     */
    @Autowired
    private ReachDiscountPromotionActivityCalculator reachDiscountPromotionActivityCalculator;
    /**
     * 多买优惠型的促销活动计算组件
     */
    @Autowired
    private MultiDiscountPromotionActivityCalculator multiDiscountPromotionActivityCalculator;
    /**
     * 单品促销型的促销活动计算组件
     */
    @Autowired
    private DirectDiscountPromotionActivityCalculator directDiscountPromotionActivityCalculator;
    /**
     * 默认的促销活动计算组件
     */
    private DefaultPromotionActivityCalculator defaultPromotionActivityCalculator;
    /**
     * 默认的运费计算组件
     */
    private DefaultFreightCalculator defaultFreightCalculator;

    /**
     * 创建总金额计算组件
     * @return
     */
    @Override
    public TotalPriceCalculator createTotalPriceCalculator() {
        return totalPriceCalculator;
    }

    /**
     * 创建促销活动计算组件
     * @param promotionActivity
     * @return
     */
    @Override
    public PromotionActivityCalculator createPromotionActivityCalculator(PromotionActivityDTO promotionActivity) {
        if(promotionActivity == null){
            return defaultPromotionActivityCalculator;
        }

        Integer promotionActivityType = promotionActivity.getType();

        if(PromotionActivityType.REACH_DISCOUNT.equals(promotionActivityType)){
            return reachDiscountPromotionActivityCalculator;
        }else if(PromotionActivityType.MULTI_DISCOUNT.equals(promotionActivityType)){
            return multiDiscountPromotionActivityCalculator;
        }else if(PromotionActivityType.DIRECT_DISCOUNT.equals(promotionActivityType)){
            return directDiscountPromotionActivityCalculator;
        }
        return defaultPromotionActivityCalculator;
    }

    /**
     * 创建运费计算组件
     * @return
     */
    @Override
    public FreightCalculator createFreightCalculator() {
        return defaultFreightCalculator;
    }
}
