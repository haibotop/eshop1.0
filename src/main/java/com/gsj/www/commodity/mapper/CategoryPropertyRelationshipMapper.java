package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.CategoryPropertyRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param categoryId 类目id
     * @return 类目与属性的关联关系
     */
    @Select("SELECT "
            + "id,"
            + "category_id,"
            + "property_id,"
            + "is_required,"
            + "property_types,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_category_property_relationship "
            + "WHERE category_id=#{categoryId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "property_id", property = "propertyId"),
            @Result(column = "is_required", property = "required"),
            @Result(column = "property_types", property = "propertyTypes"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<CategoryPropertyRelationshipDO> listByCategoryId(
            @Param("categoryId") Long categoryId);

    /**
     * 根据类目id删除类目与属性的关联关系
     * @param categoryId 类目id
     */
    @Delete("DELETE FROM commodity_category_property_relationship WHERE category_id=#{categoryId}")
    void removeByCategoryId(@Param("categoryId") Long categoryId);
}
