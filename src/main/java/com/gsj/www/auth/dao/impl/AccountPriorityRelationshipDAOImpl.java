package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.AccountPriorityRelationshipDAO;
import com.gsj.www.auth.mapper.AccountPriorityRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 账号和权限关系管理模块的DAO组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:57
 */
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {
    /**
     * 账号和权限关系管理模块的mapper组件
     */
    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long getCountByPriorityId(Long priorityId) {
        return accountPriorityRelationshipMapper.getCountByPriorityId(priorityId);
    }

}
