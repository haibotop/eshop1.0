package com.gsj.www.auth.dao;

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
}
