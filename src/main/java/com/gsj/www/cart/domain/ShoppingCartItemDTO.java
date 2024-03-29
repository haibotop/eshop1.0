package com.gsj.www.cart.domain;

import com.gsj.www.common.util.AbstractObject;
import com.gsj.www.promotion.domain.PromotionActivityDTO;

import java.util.Date;
import java.util.List;

/**
 * 购物车条目DTO类
 *
 * @author Holy
 * @create 2019 - 06 - 14 6:40
 */
public class ShoppingCartItemDTO extends AbstractObject {
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
     * 商品sku编号
     */
    private String goodsSkuCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 销售属性
     */
    private String saleProperties;
    /**
     * 商品sku售价
     */
    private Double salePrice;
    /**
     * 商品毛重
     */
    private Double grossWeight;
    /**
     * 商品长度
     */
    private Double goodsLength;
    /**
     * 商品宽度
     */
    private Double goodsWidth;
    /**
     * 商品高度
     */
    private Double goodsHeight;
    /**
     * 商品sku的销售库存
     */
    private Long saleStockQuantity;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 购买数量
     */
    private Long purchaseQuantity;
    /**
     * 促销活动集合
     */
    private List<PromotionActivityDTO> promotionActivityDTOs;
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

    public String getGoodsSkuCode() {
        return goodsSkuCode;
    }

    public void setGoodsSkuCode(String goodsSkuCode) {
        this.goodsSkuCode = goodsSkuCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSaleProperties() {
        return saleProperties;
    }

    public void setSaleProperties(String saleProperties) {
        this.saleProperties = saleProperties;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(Double goodsLength) {
        this.goodsLength = goodsLength;
    }

    public Double getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(Double goodsWidth) {
        this.goodsWidth = goodsWidth;
    }

    public Double getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(Double goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public Long getSaleStockQuantity() {
        return saleStockQuantity;
    }

    public void setSaleStockQuantity(Long saleStockQuantity) {
        this.saleStockQuantity = saleStockQuantity;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public List<PromotionActivityDTO> getPromotionActivityDTOs() {
        return promotionActivityDTOs;
    }

    public void setPromotionActivityDTOs(List<PromotionActivityDTO> promotionActivityDTOs) {
        this.promotionActivityDTOs = promotionActivityDTOs;
    }
}
