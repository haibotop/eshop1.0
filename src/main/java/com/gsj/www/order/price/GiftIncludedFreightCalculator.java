package com.gsj.www.order.price;

import com.gsj.www.commodity.domain.GoodsSkuDTO;
import com.gsj.www.commodity.service.CommodityService;
import com.gsj.www.logistics.service.LogisticsService;
import com.gsj.www.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 含赠品的运费计算组件
 * @author holy
 */
@Component
public class GiftIncludedFreightCalculator implements FreightCalculator{
    /**
     * 物流中心接口
     */
    @Autowired
    private LogisticsService logisticsService;
    /**
     * 商品中心接口
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 计算运费
     * @param item
     * @param result
     * @return
     */
    @Override
    public Double calculator(OrderItemDTO item, PromotionActivityResult result) {
        Double freight = 0.0;

        GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(item.getGoodsSkuId());
        freight += logisticsService.calculateFreight(goodsSku);

        for (OrderItemDTO giftItem : result.getOrderItem()){
            GoodsSkuDTO giftGoodsSku = commodityService.getGoodsSkuById(giftItem.getGoodsSkuId());
            freight += logisticsService.calculateFreight(giftGoodsSku);
        }
        return freight;
    }
}
