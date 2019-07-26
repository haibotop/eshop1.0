package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.AccountRoleRelationshipDAO;
import com.gsj.www.auth.mapper.AccountRoleRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
