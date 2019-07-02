package com.gsj.www.commodity.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

/**
 * 商品属性DO类
 *
 * @author Holy
 * @create 2019 - 07 - 03 7:38
 */
public class PropertyDO {
    private static final Logger logger = LoggerFactory.getLogger(PropertyDO.class);
    /**
     * id
     */
    private Long id;
    /**
     * 属性名称
     */
    private String propertyName;
    /**
     * 属性描述
     */
    private String propertyDesc;
    /**
     * 输入类型
     */
    private Integer inputType;
    /**
     * 输入可选值
     */
    private String inputValue;
    /**
     * 商品属性的创建时间
     */
    private Date gmtCreate;
    /**
     * 商品属性的修改时间
     */
    private Date gmtModified;

    public static Logger getLogger() {
        return logger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
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
        if (!(o instanceof PropertyDO)) return false;
        PropertyDO that = (PropertyDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(propertyName, that.propertyName) &&
                Objects.equals(propertyDesc, that.propertyDesc) &&
                Objects.equals(inputType, that.inputType) &&
                Objects.equals(inputValue, that.inputValue) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, propertyName, propertyDesc, inputType, inputValue, gmtCreate, gmtModified);
    }

    @Override
    public String toString() {
        return "PropertyDO{" +
                "id=" + id +
                ", propertyName='" + propertyName + '\'' +
                ", propertyDesc='" + propertyDesc + '\'' +
                ", inputType=" + inputType +
                ", inputValue='" + inputValue + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
