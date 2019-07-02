package com.gsj.www.comment.domain;

import java.util.Date;
import java.util.Objects;

/**
 * 评论聚合统计信息
 */
public class CommentAggregateDO {
    /**
     * id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 总评论数
     */
    private Long totalCommentCount;
    /**
     * 好评论数
     */
    private Long goodCommentCount;
    /**
     * 好评率
     */
    private Double goodCommentRate;
    /**
     * 晒图评论数
     */
    private Long showPicturesCommentCount;
    /**
     * 中评评论数
     */
    private Long mediumCommentCount;
    /**
     * 差评评论数
     */
    private Long badCommentCount;
    /**
     * 评论聚合统计信息的创建时间
     */
    private Date gmtCreate;
    /**
     * 评论聚合统计信息的修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getTotalCommentCount() {
        return totalCommentCount;
    }

    public void setTotalCommentCount(Long totalCommentCount) {
        this.totalCommentCount = totalCommentCount;
    }

    public Long getGoodCommentCount() {
        return goodCommentCount;
    }

    public void setGoodCommentCount(Long goodCommentCount) {
        this.goodCommentCount = goodCommentCount;
    }

    public Double getGoodCommentRate() {
        return goodCommentRate;
    }

    public void setGoodCommentRate(Double goodCommentRate) {
        this.goodCommentRate = goodCommentRate;
    }

    public Long getShowPicturesCommentCount() {
        return showPicturesCommentCount;
    }

    public void setShowPicturesCommentCount(Long showPicturesCommentCount) {
        this.showPicturesCommentCount = showPicturesCommentCount;
    }

    public Long getMediumCommentCount() {
        return mediumCommentCount;
    }

    public void setMediumCommentCount(Long mediumCommentCount) {
        this.mediumCommentCount = mediumCommentCount;
    }

    public Long getBadCommentCount() {
        return badCommentCount;
    }

    public void setBadCommentCount(Long badCommentCount) {
        this.badCommentCount = badCommentCount;
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
        if (!(o instanceof CommentAggregateDO)) return false;
        CommentAggregateDO that = (CommentAggregateDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(totalCommentCount, that.totalCommentCount) &&
                Objects.equals(goodCommentCount, that.goodCommentCount) &&
                Objects.equals(goodCommentRate, that.goodCommentRate) &&
                Objects.equals(showPicturesCommentCount, that.showPicturesCommentCount) &&
                Objects.equals(mediumCommentCount, that.mediumCommentCount) &&
                Objects.equals(badCommentCount, that.badCommentCount) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, goodsId, totalCommentCount, goodCommentCount, goodCommentRate, showPicturesCommentCount, mediumCommentCount, badCommentCount, gmtCreate, gmtModified);
    }
}
