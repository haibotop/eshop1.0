package com.gsj.www.cart.service;

import com.gsj.www.cart.domain.ShoppingCartDTO;

/**
 * 购物车管理模块的service组件接口

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

    /**
     * 查看用户的购物车中的数据
     * @param userAccountId 用户账号id
     * @return 购物车DTO对象
     */
    ShoppingCartDTO getShoppingCartDTOByUserAccountId(Long userAccountId);
}
