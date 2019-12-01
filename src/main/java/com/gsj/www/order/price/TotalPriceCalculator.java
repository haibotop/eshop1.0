package com.gsj.www.order.price;

import com.gsj.www.order.domain.OrderItemDTO;

/**
 * 商品条目总金额计算器
 * @author holy
 */
public interface TotalPriceCalculator {
    Double calculate(OrderItemDTO item);
}
