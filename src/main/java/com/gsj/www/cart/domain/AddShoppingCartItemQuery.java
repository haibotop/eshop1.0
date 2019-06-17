package com.gsj.www.cart.domain;

/**
 * 封装购物车id和购物车条目id查询类
 *
 * @author Holy
 * @create 2019 - 06 - 17 23:12
 */
public class AddShoppingCartItemQuery {
    private Long userAccountId;
    private Long goodsSkuId;

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }
}
