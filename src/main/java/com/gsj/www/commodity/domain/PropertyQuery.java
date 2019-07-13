package com.gsj.www.commodity.domain;

/**
 * 商品属性的查询条件
 *
 * @author Holy
 * @create 2019 - 07 - 03 7:51
 */
public class PropertyQuery {
    /**
     * 分页的起始位置
     */
    private Integer offset;
    /**
     * 每一页查询多少条数据
     */
    private Integer size;
    /**
     * 属性名称
     */
    private String propertyName;

    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public String getPropertyName() {
        return propertyName;
    }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
