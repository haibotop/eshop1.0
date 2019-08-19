package com.gsj.www.auth.dao;

import com.gsj.www.auth.domain.AccountPriorityRelationshipDO;

import java.util.List;

/**
 * 账号和权限关系管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:53
 */
public interface AccountPriorityRelationshipDAO {
    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    Long getCountByPriorityId(Long priorityId);

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据账号id查询账号和权限的关联关系
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     */
    List<AccountPriorityRelationshipDO> listByAccountId(Long accountId);

    /**
     * 根据账号id删除账号和权限的关联关系
     * @param accountId 账号id
     */
    void removeByAccountId(Long accountId);
}
