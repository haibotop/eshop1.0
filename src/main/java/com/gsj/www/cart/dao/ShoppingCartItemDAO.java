package com.gsj.www.cart.dao;

import com.gsj.www.cart.domain.ShoppingCartItemDO;

import java.util.List;

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

    /**
     * 查询购物车中的所有条目
     * @param shoppingCartId 购物车id
     * @return 商品条目
     */
    List<ShoppingCartItemDO> listShoppingCartItemByCartId(Long shoppingCartId);

    /**
     * 删除购物车条目
     * @param id 购物车条目id
     */
    Boolean remove(Long id);
}
