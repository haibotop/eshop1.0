package com.gsj.www.cart.domain;

import java.util.Date;
import java.util.Objects;

/**
 * 购物车条目DO类
 *
 * @author Holy
 * @create 2019 - 06 - 28 7:55
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
    private Date gmtModified;

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

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartItemDO)) return false;
        ShoppingCartItemDO that = (ShoppingCartItemDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(shoppingCartId, that.shoppingCartId) &&
                Objects.equals(goodsSkuId, that.goodsSkuId) &&
                Objects.equals(purchaseQuantity, that.purchaseQuantity) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, shoppingCartId, goodsSkuId, purchaseQuantity, gmtCreate, gmtModified);
    }
}
