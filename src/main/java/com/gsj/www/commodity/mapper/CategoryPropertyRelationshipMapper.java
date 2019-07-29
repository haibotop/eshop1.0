package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.CategoryPropertyRelationshipDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 类目属性关系管理mapper组件
 */
@Mapper
public interface CategoryPropertyRelationshipMapper {
    /**
     * 新增类目属性关系
     * @param relation 类目属性关系
     */
    @Insert("insert info commodity_category_property_relationship(" +
            "category_id," +
            "property_id," +
            "is_required," +
            "property_types," +
            "gmt_create," +
            "gmt_modified" +
            ") values(" +
            "#{categoryId}," +
            "#{propertyId}," +
            "#{required}," +
            "#{propertyTypes}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(CategoryPropertyRelationshipDO relation);
}
