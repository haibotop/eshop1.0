package com.gsj.www.order.price;

import com.gsj.www.order.domain.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * 总金额默认计算器
 * @author holy
 */
@Component
public class DefaultTotalPriceCalculator implements TotalPriceCalculator {
    /**
     * 计算商品的总金额
     * @param item
     * @return
     */
    @Override
    public Double calculate(OrderItemDTO item) {
        return item.getPurchasePrice() * item.getPurchaseQuantity();
    }
}
