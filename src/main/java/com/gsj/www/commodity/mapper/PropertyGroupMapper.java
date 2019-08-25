package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.PropertyGroupDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     */
    @Select("SELECT "
            + "id,"
            + "name,"
            + "category_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_property_group "
            + "WHERE category_id=#{categoryId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PropertyGroupDO> listByCategoryId(
            @Param("categoryId") Long categoryId);

    /**
     * 根据类目id删除属性分组
     * @param categoryId 类目id
     */
    @Delete("DELETE FROM commodity_property_group WHERE category_id=#{categoryId}")
    void removeByCategoryId(@Param("categoryId") Long categoryId);
}
