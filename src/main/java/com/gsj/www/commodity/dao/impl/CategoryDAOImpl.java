package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.CategoryDAO;
import com.gsj.www.commodity.domain.CategoryDO;
import com.gsj.www.commodity.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目管理DAO组件
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
    /**
     * 类目管理mapper组件
     */
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询根类目
     * @return 根类目集合
     */
    @Override
    public List<CategoryDO> listRoots() {
        try{
            return categoryMapper.listRoots();
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     */
    @Override
    public List<CategoryDO> listChildren(Long id) {
        try {
            return categoryMapper.listChildren(id);
        }catch (Exception e) {
            logger.error("error",e);
            return null;
        }
    }

    @Override
    public Long save(CategoryDO category) {
        try {
            categoryMapper.save(category);
            return category.getId();
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 根据id查询类目
     * @param id 类目id
     * @return 类目
     */
    public CategoryDO getById(Long id) {
        try {
            return categoryMapper.getById(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
