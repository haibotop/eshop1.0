package com.gsj.www.inventory.constant;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 库存商品更新操作
 *
 * @author Holy
 * @create 2019 - 07 - 15 22:52
 */
public class GoodsStockUpdateOperation {
    /**
     * 提交订单
     */
    public static final Integer SUBMIT_ORDER = 1;
    /**
     * 支付订单
     */
    public static final Integer PAY_ORDER = 2;
    /**
     * 取消订单
     */
    public static final Integer CANCEL_ORDER = 3;

    private GoodsStockUpdateOperation(){

    }
}
