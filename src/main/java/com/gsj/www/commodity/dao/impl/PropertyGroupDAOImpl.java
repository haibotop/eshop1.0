package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.PropertyGroupDAO;
import com.gsj.www.commodity.domain.PropertyGroupDO;
import com.gsj.www.commodity.mapper.PropertyGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性分组管理DAO组件
 */
@Repository
public class PropertyGroupDAOImpl implements PropertyGroupDAO {
    private static final Logger logger = LoggerFactory.getLogger(PropertyGroupDAOImpl.class);
    /**
     * 属性分组管理mapper组件
     */
    @Autowired
    private PropertyGroupMapper propertyGroupMapper;

    /**
     * 新增属性分组
     * @param group 属性分组
     */
    @Override
    public Long save(PropertyGroupDO group) {
        try {
            propertyGroupMapper.save(group);
            return group.getId();
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     */
    public List<PropertyGroupDO> listByCategoryId(Long categoryId) {
        try {
            return propertyGroupMapper.listByCategoryId(categoryId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据类目id删除属性分组
     * @param categoryId 类目id
     */
    public void removeByCategoryId(Long categoryId) throws Exception {
        propertyGroupMapper.removeByCategoryId(categoryId);
    }
}
