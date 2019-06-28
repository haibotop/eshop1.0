package com.gsj.www.cart.service;

/**
 * 购物车管理模块的service组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 28 7:57
 */
public interface ShoppingCartService {
    /**
     * 添加购物车商品条目
     * @param userAccountId 用户账号id
     * @param goodsSkuId 商品sku id
     * @return 处理结果
     */
    Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId);
}
