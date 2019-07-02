package com.gsj.www.commodity.service;

import com.gsj.www.commodity.domain.PropertyDTO;
import com.gsj.www.commodity.domain.PropertyQuery;

import java.util.List;

/**
 * 商品属性管理模块的servi组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 03 7:50
 */
public interface PropertyService {
    /**
     * 分页查询商品属性
     * @param propertyQuery 查询条件
     * @return 商品属性
     */
    List<PropertyDTO> listPropertiesByPage(PropertyQuery propertyQuery);

    /**
     * 新增商品属性
     * @param propertyDTO 商品属性DO对象
     */
    Boolean saveProperty(PropertyDTO propertyDTO);

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     */
    PropertyDTO getPropertyById(Long id);

    /**
     * 更新商品属性
     * @param propertyDTO 商品属性DO对象
     */
    Boolean updateProperty(PropertyDTO propertyDTO);
}
