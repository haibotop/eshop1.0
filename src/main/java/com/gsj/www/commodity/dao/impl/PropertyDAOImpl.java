package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.PropertyDAO;
import com.gsj.www.commodity.domain.PropertyDO;
import com.gsj.www.commodity.domain.PropertyQuery;
import com.gsj.www.commodity.mapper.PropertyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性管理模块的DAO组件
 */
@Repository
public class PropertyDAOImpl implements PropertyDAO {
    private static final Logger logger = LoggerFactory.getLogger(PropertyDAOImpl.class);
    /**
     * 商品属性管理模块的mapper组件
     */
    @Autowired
    private PropertyMapper propertyMapper;

    /**
     * 分页查询商品属性
     * @param propertyQuery 查询条件
     * @return 商品属性集合
     */
    @Override
    public List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery) {
        try {
            return propertyMapper.listPropertiesByPage(propertyQuery);
        }catch (Exception e){
            logger.error("error",e);
        }
        return new ArrayList<PropertyDO>();
    }

    /**
     * 新增商品属性
     * @param propertyDO 商品属性DO对象
     * @return 处理结果
     */
    @Override
    public Boolean saveProperty(PropertyDO propertyDO) {
        try {
            propertyMapper.saveProperty(propertyDO);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     */
    @Override
    public PropertyDO getPropertyById(Long id) {
        try {
            return propertyMapper.getPropertyById(id);
        }catch (Exception e){
            logger.error("error",e);
        }
        return new PropertyDO();
    }

    /**
     * 更新商品属性
     * @param propertyDO 商品属性DO对象
     * @return 处理结果
     */
    @Override
    public Boolean updateProperty(PropertyDO propertyDO) {
        try {
            propertyMapper.updateProperty(propertyDO);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }
}
