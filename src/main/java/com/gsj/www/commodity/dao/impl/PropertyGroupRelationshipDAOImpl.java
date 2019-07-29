package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.PropertyGroupDAO;
import com.gsj.www.commodity.dao.PropertyGroupRelationshipDAO;
import com.gsj.www.commodity.domain.PropertyGroupDO;
import com.gsj.www.commodity.domain.PropertyGroupRelationshipDO;
import com.gsj.www.commodity.mapper.PropertyGroupRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 属性分组与属性关系管理DAO组件
 */
@Repository
public class PropertyGroupRelationshipDAOImpl implements PropertyGroupRelationshipDAO {
    private static final Logger logger = LoggerFactory.getLogger(PropertyGroupRelationshipDAO.class);
    /**
     * 属性分组与属性关系管理mapper组件
     */
    @Autowired
    private PropertyGroupRelationshipMapper propertyGroupRelationshipMapper;

    /**
     * 新增属性分组与属性关系
     * @param relation 属性分组与属性关系
     * @return
     */
    @Override
    public Boolean save(PropertyGroupRelationshipDO relation) {
        try{
            propertyGroupRelationshipMapper.save(relation);
            return true;
        }catch (Exception e) {
            logger.error("error",e);
            return false;
        }
    }
}
