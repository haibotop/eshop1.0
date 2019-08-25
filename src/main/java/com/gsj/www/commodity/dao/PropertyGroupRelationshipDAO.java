package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.PropertyGroupRelationshipDO;

import java.util.List;

/**
 * 属性分组与属性关系管理DAO组件接口
 */
public interface PropertyGroupRelationshipDAO {
    /**
     * 新增属性分组与属性关系
     * @param relation 属性分组与属性关系
     * @return
     */
    Boolean save(PropertyGroupRelationshipDO relation);

    /**
     * 根据属性分组id查询属性分组与属性的关联关系
     * @param propertyGroupId 属性分组id
     * @return 属性分组与属性的关联关系
     */
    List<PropertyGroupRelationshipDO> listByPropertyGroupId(Long propertyGroupId);

    /**
     * 根据属性分组id删除属性分组与属性的关联关系
     * @param propertyGroupId 属性分组id
     */
    void removeByPropertyGroupId(Long propertyGroupId) throws Exception;
}
