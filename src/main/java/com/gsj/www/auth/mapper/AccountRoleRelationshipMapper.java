package com.gsj.www.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 账号角色关系管理模块mapper组件
 */
@Mapper
public interface AccountRoleRelationshipMapper {
    /**
     * 根据角色id来查询记录数
     * @param roleId 角色id
     * @return 记录数
     */
    @Select("select count(*) " +
            "from auth_account_role_relationship " +
            "where role_id=#{roleId}")
    Long countByRoleId(@Param("roleId") Long roleId);
}
