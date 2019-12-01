package com.gsj.www.order.price;

import com.gsj.www.order.domain.OrderItemDTO;

/**
 * 运费计算组件接口
 * @author holy
 */
public interface FreightCalculator {
    Double calculator(OrderItemDTO item, PromotionActivityResult result);
}
