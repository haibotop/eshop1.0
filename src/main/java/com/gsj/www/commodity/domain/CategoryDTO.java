package com.gsj.www.commodity.domain;

import com.gsj.www.common.util.AbstractObject;

import java.util.Date;
import java.util.List;

/**
 * 类目DTO类
 */
public class CategoryDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类目描述
     */
    private String description;
    /**
     * 是否为叶子类目
     */
    private Integer isLeaf;
    /**
     * 父类目id
     */
    private Long parentId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 类目与属性的关联关系
     */
    private List<CategoryPropertyRelationshipDTO> propertyRelations;
    /**
     * 类目关联的属性
     */
    private List<PropertyDTO> properties;
    /**
     * 属性分组
     */
    private List<PropertyGroupDTO> propertyGroups;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public List<CategoryPropertyRelationshipDTO> getPropertyRelations() {
        return propertyRelations;
    }

    public void setPropertyRelations(List<CategoryPropertyRelationshipDTO> propertyRelations) {
        this.propertyRelations = propertyRelations;
    }

    public List<PropertyGroupDTO> getPropertyGroups() {
        return propertyGroups;
    }

    public void setPropertyGroups(List<PropertyGroupDTO> propertyGroups) {
        this.propertyGroups = propertyGroups;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }
}
