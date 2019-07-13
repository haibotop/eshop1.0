package com.gsj.www.auth.mapper;

import com.gsj.www.auth.domain.RolePriorityRelationshipDO;
import org.apache.ibatis.annotations.*;

/**
 * 角色和权限关系管理模块的mapper组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:52
 */
@Mapper
public interface RolePriorityRelationshipMapper {
    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) "
            + "FROM auth_role_priority_relationship "
            + "WHERE priority_id=#{priorityId}")
    Long getCountByPriorityId(@Param("priorityId") Long priorityId);

    /**
     * 新增角色和权限的关联关系
     * @param rolePriorityRelationshipDO
     */
    @Insert("INSERT INTO auth_role_priority_relationship("
            + "role_id,"
            + "priority_id,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{roleId},"
            + "#{priorityId},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(RolePriorityRelationshipDO rolePriorityRelationshipDO);
}
