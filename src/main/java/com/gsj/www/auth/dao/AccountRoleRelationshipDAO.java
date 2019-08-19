package com.gsj.www.auth.dao;

import com.gsj.www.auth.domain.AccountRoleRelationshipDO;

import java.util.List;

/**
 * 账号角色关系管理模块DAO组件接口
 * @author Holy
 * @create 2019 - 07 - 25 22:38
 */
public interface AccountRoleRelationshipDAO {
    /**
     * 根据角色id来查询记录数
     * @param roleId 角色id
     * @return 记录数
     */
    Long countByRoleId(Long roleId);

    /**
     * 根据账号id查询账号和角色关联关系
     * @param accountId 账号id
     * @return 账号和角色关联关系
     */
    List<AccountRoleRelationshipDO> listByAccountId(Long accountId);

    /**
     * 新增账号和角色的关联关系
     * @param relation 账号和角色的关联关系
     */
    void save(AccountRoleRelationshipDO relation);

    /**
     * 根据账号id删除账号和角色的关联关系
     * @param accountId 账号id
     */
    void removeByAccountId(Long accountId);
}
