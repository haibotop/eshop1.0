package com.gsj.www.cart.dao;

import com.gsj.www.cart.domain.ShoppingCartItemDO;

/**
 * 购物车条目管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 14 23:25
 */
public interface ShoppingCartItemDAO {
    /**
     * 新增购物车条目
     * @param shoppingCartItemDO 购物车条目DO对象
     * @return
     */
    Boolean saveShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO);

    /**
     * 根据商品sku id查询购物车中是否存在商品条目
     * @param shoppingCartId 购物车id
     * @param goodsSkuId 商品sku id
     * @return 商品条目对象
     */
    ShoppingCartItemDO getShoppingCartItemByGoodsSkuId(
            Long shoppingCartId, Long goodsSkuId);

    /**
     * 更新购物车条目
     * @param shoppingCartItemDO 购物车条目DO对象
     * @return
     */
    Boolean updateShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO);
}