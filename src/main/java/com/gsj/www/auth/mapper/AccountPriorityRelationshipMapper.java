package com.gsj.www.auth.mapper;

import com.gsj.www.auth.domain.AccountPriorityRelationshipDO;
import org.apache.ibatis.annotations.*;

/**
 * 账号和权限关系管理模块的mapper组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:47
 */
@Mapper
public interface AccountPriorityRelationshipMapper {
    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) "
            + "FROM auth_account_priority_relationship "
            + "WHERE priority_id=#{priorityId}")
    Long getCountByPriorityId(@Param("priorityId") Long priorityId);

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    @Insert("INSERT INTO auth_account_priority_relationship(" +
            "account_id," +
            "priority_id," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES(" +
            "#{accountId}," +
            "#{priorityId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);
}
