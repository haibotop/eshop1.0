package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.PropertyGroupRelationshipDO;

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
}
