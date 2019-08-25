package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.CategoryPropertyRelationshipDO;

import java.util.List;

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

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param categoryId 类目id
     * @return 类目与属性的关联关系
     */
    List<CategoryPropertyRelationshipDO> listByCategoryId(Long categoryId);

    /**
     * 根据类目id删除类目与属性的关联关系
     * @param categoryId 类目id
     */
    void removeByCategoryId(Long categoryId) throws Exception;
}
