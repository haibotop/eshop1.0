package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.RolePriorityRelationshipDAO;
import com.gsj.www.auth.mapper.RolePriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 角色和权限关系管理模块的DAO组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 7:02
 */
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO{
    /**
     * 角色和权限关系管理模块的mapper组件
     */
    @Autowired
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long getCountByPriorityId(Long priorityId) {
        return rolePriorityRelationshipMapper.getCountByPriorityId(priorityId);
    }
}
