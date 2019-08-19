package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.AccountRoleRelationshipDAO;
import com.gsj.www.auth.domain.AccountRoleRelationshipDO;
import com.gsj.www.auth.mapper.AccountRoleRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号角色关系管理模块DAO组件
 *
 * @author Holy
 * @create 2019 - 07 - 25 22:54
 */
@Repository
public class AccountRoleRelationshipDAOImpl implements AccountRoleRelationshipDAO{
    private static final Logger logger = LoggerFactory.getLogger(AccountRoleRelationshipDAOImpl.class);

    /**
     * 账号角色关系管理模块mapper组件
     */
    @Autowired
    private AccountRoleRelationshipMapper accountRoleRelationMapper;

    /**
     * 根据角色id来查询记录数
     * @param roleId 角色id
     * @return 记录数
     */
    @Override
    public Long countByRoleId(Long roleId) {
        try {
            return accountRoleRelationMapper.countByRoleId(roleId);
        } catch (Exception e) {
            logger.error("error", e);
            return 0L;
        }
    }

    /**
     * 根据账号id查询账号和角色关联关系
     * @param accountId 账号id
     * @return 账号和角色关联关系
     */
    @Override
    public List<AccountRoleRelationshipDO> listByAccountId(Long accountId) {
        return accountRoleRelationMapper.listByAccountId(accountId);
    }

    /**
     * 新增账号和角色的关联关系
     * @param relation 账号和角色的关联关系
     */
    @Override
    public void save(AccountRoleRelationshipDO relation) {
        accountRoleRelationMapper.save(relation);
    }

    /**
     * 根据账号id删除账号和角色的关联关系
     * @param accountId 账号id
     */
    @Override
    public void removeByAccountId(Long accountId) {
        accountRoleRelationMapper.removeByAccountId(accountId);
    }

}
