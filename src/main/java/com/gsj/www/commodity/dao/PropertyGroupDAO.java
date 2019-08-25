package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.PropertyGroupDO;

import java.util.List;

/**
 * 属性分组管理DAO组件接口
 */
public interface PropertyGroupDAO {
    /**
     * 新增属性分组
     * @param group 属性分组
     */
    Long save(PropertyGroupDO group);

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     */
    List<PropertyGroupDO> listByCategoryId(Long categoryId);

    /**
     * 根据类目id删除属性分组
     * @param categoryId 类目id
     */
    void removeByCategoryId(Long categoryId) throws Exception;

}
