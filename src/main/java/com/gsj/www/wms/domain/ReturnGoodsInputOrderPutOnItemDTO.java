package com.gsj.www.wms.domain;

import java.util.Date;

/**
 * 退货入库单商品上架条目DTO类
 */
public class ReturnGoodsInputOrderPutOnItemDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 退货入库单条目id
     */
    private Long returnGoodsInputOrderItemId;
    /**
     * 货位id
     */
    private Long goodsAllocationId;
    /**
     * 商品上架数量
     */
    private Long putOnShelvesCount;
    /**
     * 退货入库单商品上架条目的创建时间
     */
    private Date gmtCreate;
    /**
     * 退货入库单商品上架条目的修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReturnGoodsInputOrderItemId() {
        return returnGoodsInputOrderItemId;
    }

    public void setReturnGoodsInputOrderItemId(Long returnGoodsInputOrderItemId) {
        this.returnGoodsInputOrderItemId = returnGoodsInputOrderItemId;
    }

    public Long getGoodsAllocationId() {
        return goodsAllocationId;
    }

    public void setGoodsAllocationId(Long goodsAllocationId) {
        this.goodsAllocationId = goodsAllocationId;
    }

    public Long getPutOnShelvesCount() {
        return putOnShelvesCount;
    }

    public void setPutOnShelvesCount(Long putOnShelvesCount) {
        this.putOnShelvesCount = putOnShelvesCount;
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
}
