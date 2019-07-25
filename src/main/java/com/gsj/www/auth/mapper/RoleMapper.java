package com.gsj.www.auth.mapper;

import com.gsj.www.auth.domain.RoleDO;
import com.gsj.www.auth.domain.RoleQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色管理模块mapper组件
 */
@Mapper
public interface RoleMapper {
    /**
     * 分页插叙角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    @Select("<script>" +
            "select " +
            "a.id," +
            "a.code," +
            "a.name," +
            "a.remark," +
            "a.gmt_create," +
            "a.gmt_modified " +
            "from auth_role a," +
            "(" +
            "select id" +
            "from auth_role " +
            "where 1=1 " +
            "<if test='code != null'>" +
            "and code like '${code}%' " +
            "</if>" +
            "<if test='name != null'>" +
            "and name like '${name}%' " +
            "</if>" +
            "limit #{offset},#{size}" +
            ") b " +
            "where a.id=b.id " +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id=true),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<RoleDO> listByPage(RoleQuery query);

    /**
     * 根据id查询角色DO对象
     * @param id 角色id
     * @return 角色DO对象
     */
    @Select("select " +
            "id," +
            "code," +
            "name," +
            "remark," +
            "gmt_create," +
            "gmt_modified " +
            "from auth_role " +
            "where id=#{id}")
    @Results({
            @Result(column = "id", property = "id", id=true),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    RoleDO getById(@Param("id") Long id);

    /**
     * 新增角色
     * @param role 角色DO对象
     */
    @Insert("insert into auth_role(" +
            "code," +
            "name," +
            "remark," +
            "gmt_create," +
            "gmt_modified" +
            ") values(" +
            "#{code}," +
            "#{name}," +
            "#{remark}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(RoleDO role);

    /**
     * 更新角色
     * @param role 角色DO对象
     */
    @Update("update auth_role set" +
            "code=#{code}," +
            "name=#{name}," +
            "remark=#{remark}," +
            "gmt_modified=#{gmtModified} " +
            "where id=#{id}")
    void update(RoleDO role);

    /**
     * 删除角色
     * @param id 角色id
     */
    @Delete("delete from auth_role where id=#{id}")
    void remove(@Param("id") Long id);
}
