package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.AccountPriorityRelationshipDAO;
import com.gsj.www.auth.domain.AccountPriorityRelationshipDO;
import com.gsj.www.auth.mapper.AccountPriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号和权限关系管理模块的DAO组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:57
 */
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(RolePriorityRelationshipDAOImpl.class);
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
        try {
            return accountPriorityRelationshipMapper.getCountByPriorityId(priorityId);
        }catch (Exception e){
            logger.error("error",e);
        }
        return 0L;
    }

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    @Override
    public Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO) {
        try{
            accountPriorityRelationshipMapper.save(accountPriorityRelationshipDO);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 根据账号id查询账号和权限的关联关系
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     */
    @Override
    public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) {
        return accountPriorityRelationshipMapper.listByAccountId(accountId);
    }

    /**
     * 根据账号id删除账号和权限的关联关系
     * @param accountId 账号id
     */
    @Override
    public void removeByAccountId(Long accountId) {
        accountPriorityRelationshipMapper.removeByAccountId(accountId);
    }

}
