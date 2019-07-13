package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.PropertyDO;
import com.gsj.www.commodity.domain.PropertyQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品属性管理模块的mapper组件
 *
 * @author Holy
 * @create 2019 - 07 - 05 22:08
 */
@Mapper
public interface PropertyMapper {
    /**
     * 分页查询商品属性
     * @param propertyQuery 查询条件
     * @return商品属性
     */
    @Select("<script>" +
            "SELECT " +
            "a.id," +
            "a.property_name," +
            "a.property_desc," +
            "a.input_type," +
            "a.input_values," +
            "a.gmt_create," +
            "a.gmt_modified " +
            "FROM commodity_property a, " +
            "(" +
            "SELECT id FROM cmmodity_property " +
            "<if test='propertyName != null'>" +
            "WHERE property_name like '${propertyName}%' " +
            "</if>" +
            "LIMIT #{offset},#{size} " +
            ") b" +
            "WHERE a.id=b.id" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "property_name",property = "propertyName"),
            @Result(column = "property_desc",property = "propertyDesc"),
            @Result(column = "input_type",property = "inputType"),
            @Result(column = "input_values",property = "inputValues"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified")
    })
    List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery);

    /**
     * 插入商品属性
     * @param propertyDO
     */
    @Insert("INSERT INTO commodity_property(" +
            "property_name," +
            "property_desc," +
            "input_type," +
            "input_vlaues," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES(" +
            "#{propertyName}," +
            "#{propertyDesc}," +
            "#{inputType}," +
            "#{inputValues}," +
            "#{gmt_create}," +
            "#{gmt_modified}")
    @Options(keyProperty = "id",keyColumn = "id",useGeneratedKeys = true)
    void saveProperty(PropertyDO propertyDO);

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     */
    @Select("SELECT "
            + "id,"
            + "property_name,"
            + "property_desc,"
            + "input_type,"
            + "input_values,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_property "
            + "WHERE id=#{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "property_name", property = "propertyName"),
            @Result(column = "property_desc", property = "propertyDesc"),
            @Result(column = "input_type", property = "inputType"),
            @Result(column = "input_values", property = "inputValues"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    PropertyDO getPropertyById(@Param("id") Long id);

    /**
     * 更新商品属性
     * @param propertyDO 商品属性DO对象
     */
    @Update("UPDATE commodity_property SET "
            + "property_desc=#{propertyDesc},"
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void updateProperty(PropertyDO propertyDO);
}
