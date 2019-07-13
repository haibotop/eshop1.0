package com.gsj.www.auth.dao;

import com.gsj.www.auth.domain.RolePriorityRelationshipDO;

/**
 * 角色和权限关系管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:55
 */
public interface RolePriorityRelationshipDAO {
    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    Long getCountByPriorityId(Long priorityId);

    /**
     * 新增角色和权限的关联关系
     */
    Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO);
}
