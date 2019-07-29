package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.PropertyGroupDO;

/**
 * 属性分组管理DAO组件接口
 */
public interface PropertyGroupDAO {
    /**
     * 新增属性分组
     * @param group 属性分组
     */
    Long save(PropertyGroupDO group);
}
