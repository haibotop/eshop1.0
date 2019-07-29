package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.PropertyGroupDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 属性分组管理mapper组件
 */
@Mapper
public interface PropertyGroupMapper {
    /**
     * 新增属性分组
     * @param group 属性分组
     */
    @Insert("insert info commodity_property_group(" +
            "name," +
            "category_id," +
            "gmt_create," +
            "gmt_modified" +
            ") values(" +
            "#{name}," +
            "#{categoryId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    void save(PropertyGroupDO group);
}
