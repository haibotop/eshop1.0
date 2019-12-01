package com.gsj.www.order.price;

import com.gsj.www.commodity.domain.GoodsSkuDTO;
import com.gsj.www.order.domain.OrderItemDTO;

/**
 * 商品类型的促销活动计算组件的基类
 * @author holy
 */
public class AbstractGiftPromotionActivityCalculator {
    protected OrderItemDTO createOrderItem(GoodsSkuDTO goodsSkuDTO){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setGoodsId(goodsSkuDTO.getGoodsId());
        orderItemDTO.setGoodsSkuCode(goodsSkuDTO.getGoodsSkuCode());
        orderItemDTO.setGoodsName(goodsSkuDTO.getGoodsName());
        orderItemDTO.setSaleProperties(goodsSkuDTO.getSaleProperties());
        orderItemDTO.setGoodsGrossWeight(goodsSkuDTO.getGrossWeight());
        orderItemDTO.setPurchaseQuantity(1L);
        orderItemDTO.setPurchasePrice(0.0);
        orderItemDTO.setGoodsLength(goodsSkuDTO.getGoodsLength());
        orderItemDTO.setGoodsWidth(goodsSkuDTO.getGoodsWidth());
        orderItemDTO.setGoodsHeight(goodsSkuDTO.getGoodsHeight());
        return orderItemDTO;
    }
}
