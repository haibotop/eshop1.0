package com.gsj.www.commodity.service.impl;

import com.gsj.www.commodity.dao.CategoryDAO;
import com.gsj.www.commodity.dao.CategoryPropertyRelationshipDAO;
import com.gsj.www.commodity.dao.PropertyGroupDAO;
import com.gsj.www.commodity.dao.PropertyGroupRelationshipDAO;
import com.gsj.www.commodity.domain.CategoryDO;
import com.gsj.www.commodity.domain.CategoryDTO;
import com.gsj.www.commodity.service.CategoryService;
import com.gsj.www.common.util.DateProvider;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 类目管理DAO组件
     */
    @Autowired
    private CategoryDAO categoryDAO;
    /**
     * 类目属性关系DAO组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;
    /**
     * 属性分组DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;
    /**
     * 属性分组与属性关系DAO组件
     */
    @Autowired
    private PropertyGroupRelationshipDAO propertyGroupRelationshipDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 查询根类目
     * @return 根类目集合
     */
    @Override
    public List<CategoryDTO> listRoots() {
        try{
            List<CategoryDO> categories = categoryDAO.listRoots();
            List<CategoryDTO> resultCategories = ObjectUtils.convertList(categories,CategoryDTO.class);

            return resultCategories;
        }catch (Exception e) {
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
    public List<CategoryDTO> listChildren(Long id) {
        try {
            List<CategoryDO> categories = categoryDAO.listChildren(id);
            List<CategoryDTO> resultCategories = ObjectUtils.convertList(categories,CategoryDTO.class);
            return resultCategories;
        }catch (Exception e) {
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 新增类目
     * @param category 类目
     * @return 处理结果
     */
    @Override
    public Boolean save(CategoryDTO category) {
        try{
            //保存类目的基本信息
            //保存类目与属性之间的关联关系
            //保存属性分组
            return true;
        }catch (Exception e) {
            logger.error("error",e);
            return false;
        }
    }
}
