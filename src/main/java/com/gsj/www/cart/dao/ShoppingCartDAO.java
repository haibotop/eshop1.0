package com.gsj.www.cart.dao;

import com.gsj.www.cart.domain.ShoppingCartDO;

/**
 * 购物车管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 14 23:22
 */
public interface ShoppingCartDAO {
    /**
     * 根据用户账号id查询购物车
     * @param userAccountId 用户账号id
     * @return 购物车
     */
    ShoppingCartDO getShoppingCartByUserAccountId(Long userAccountId);

    /**
     * 新增购物车
     * @param shoppingCartDO 购物车对象
     * @return
     */
    Boolean saveShoppingCart(ShoppingCartDO shoppingCartDO);
}
