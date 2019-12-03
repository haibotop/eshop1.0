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
 * 赠品促销类型的促销活动的价格计算组件
 * @author holy
 */
@Component
public class DirectGiftPromotionActivityCalculator extends AbstractGiftPromotionActivityCalculator implements PromotionActivityCalculator{
    /**
     * 商品中心接口
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 促销活动的价格计算
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) {
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");

        PromotionActivityResult result = new PromotionActivityResult();

        for (int i = 0; i < giftGoodsSkuIds.size(); i++){
            Long goodsSkuId = giftGoodsSkuIds.getLong(i);
            GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
            result.getOrderItem().add(createOrderItem(goodsSku));
        }

        return result;
    }
}
