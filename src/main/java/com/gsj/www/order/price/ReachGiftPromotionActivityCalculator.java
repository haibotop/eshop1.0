package com.gsj.www.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gsj.www.commodity.domain.GoodsSkuDTO;
import com.gsj.www.commodity.service.CommodityService;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 满赠类型的促销活动的处理器
 * @author holy
 */
@Component
public class ReachGiftPromotionActivityCalculator extends AbstractGiftPromotionActivityCalculator implements PromotionActivityCalculator{
    /**
     * 商品中心接口
     */
    @Autowired
    private CommodityService commodityService;

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
        Double thresholdAmount = rule.getDouble("thresholdAmount");
        JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");

        if(totalAmount > thresholdAmount){
            PromotionActivityResult result = new PromotionActivityResult();

            for(int i = 0; i < giftGoodsSkuIds.size(); i++){
                Long goodsSkuId = giftGoodsSkuIds.getLong(i);
                GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
                result.getOrderItem().add(createOrderItem(goodsSku));
            }
            return result;
        }
        return new PromotionActivityResult();
    }
}
