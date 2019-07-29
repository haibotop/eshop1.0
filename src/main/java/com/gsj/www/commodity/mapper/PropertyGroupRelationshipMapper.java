package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.PropertyGroupRelationshipDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 属性分组与属性关系管理mapper组件
 */
@Mapper
public interface PropertyGroupRelationshipMapper {
    /**
     * 新增属性分组与属性关系
     * @param relation 属性分组与属性关系
     */
    @Insert("insert into commodity_property_group_relationship(" +
            "property_group_id," +
            "property_id," +
            "is_required," +
            "property_types," +
            "gmt_create," +
            "gmt_modified" +
            ") values(" +
            "#{propertyGroupId}," +
            "#{propertyId}," +
            "#{isRequired}," +
            "#{propertyTypes}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id",keyProperty = "id",useGeneratedKeys = true)
    void save(PropertyGroupRelationshipDO relation);
}
