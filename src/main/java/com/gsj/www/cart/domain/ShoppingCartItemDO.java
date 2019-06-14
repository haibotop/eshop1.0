package com.gsj.www.cart.domain;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 购物车条目DO类
 *
 * @author Holy
 * @create 2019 - 06 - 14 6:40
 */
public class ShoppingCartItemDO {
    /**
     * id
     */
    private Long id;
    /**
     * 购物车id
     */
    private Long shoppingCartId;
    /**
     * 商品sku id
     */
    private Long goodsSkuId;
    /**
     * 购买数量
     */
    private Long purchaseQuantity;
    /**
     * 购物车条目的创建时间
     */
    private Date gmtCreate;
    /**
     * 购物车条目的修改时间
     */
    private Data gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public Long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Data getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
