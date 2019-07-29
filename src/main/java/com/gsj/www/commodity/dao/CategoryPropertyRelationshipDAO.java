package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.CategoryPropertyRelationshipDO;

/**
 * 类目属性关系管理DAO组件接口
 */
public interface CategoryPropertyRelationshipDAO {
    /**
     * 新增类目属性关系
     * @param relation 类目属性关系
     * @return
     */
    Boolean save(CategoryPropertyRelationshipDO relation);
}
