package com.gsj.www.commodity.mapper;

import com.gsj.www.commodity.domain.CategoryDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 类目管理模块mapper组件
 */
@Mapper
public interface CategoryMapper {
    /**
     * 查询根类目
     * @return 根类目集合
     */
    @Select("select " +
            "id," +
            "name," +
            "description," +
            "parent_id," +
            "is_leaf," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_category " +
            "where parent_id is NULL")
    @Results({
            @Result(column = "id",property = "id",id=true),
            @Result(column = "name",property = "name"),
            @Result(column = "description",property = "description"),
            @Result(column = "parent_id",property = "parentId"),
            @Result(column = "is_leaf",property = "isLeaf"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified")
    })
    List<CategoryDO> listRoots();

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     */
    @Select("select " +
            "id," +
            "name," +
            "description," +
            "is_leaf," +
            "gmt_create," +
            "gmt_modified " +
            "from commodity_category " +
            "where parent_id=#{id}")
    @Results({
            @Result(column = "id",property = "id",id=true),
            @Result(column = "name",property = "name"),
            @Result(column = "description",property = "description"),
            @Result(column = "parent_id",property = "parentId"),
            @Result(column = "is_leaf",property = "isLeaf"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified")
    })
    List<CategoryDO> listChildren(@Param("id") Long id);
}
