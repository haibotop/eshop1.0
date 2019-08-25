package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.CategoryPropertyRelationshipDAO;
import com.gsj.www.commodity.domain.CategoryPropertyRelationshipDO;
import com.gsj.www.commodity.mapper.CategoryPropertyRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目属性管理DAO组件
 */
@Repository
public class CategoryPropertyRelationshipDAOImpl implements CategoryPropertyRelationshipDAO {
    private static final Logger logger = LoggerFactory.getLogger(CategoryPropertyRelationshipDAOImpl.class);
    /**
     * 类目属性管理mapper组件
     */
    @Autowired
    private CategoryPropertyRelationshipMapper categoryPropertyRelationshipMapper;

    /**
     * 新增类目属性关系
     * @param relation 类目属性关系
     * @return 处理结果
     */
    @Override
    public Boolean save(CategoryPropertyRelationshipDO relation) {
        try {
            categoryPropertyRelationshipMapper.save(relation);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param categoryId 类目id
     * @return 类目与属性的关联关系
     */
    public List<CategoryPropertyRelationshipDO> listByCategoryId(Long categoryId) {
        try {
            return categoryPropertyRelationshipMapper.listByCategoryId(categoryId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据类目id删除类目与属性的关联关系
     * @param categoryId 类目id
     */
    public void removeByCategoryId(Long categoryId) throws Exception {
        categoryPropertyRelationshipMapper.removeByCategoryId(categoryId);
    }
}
