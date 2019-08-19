package com.gsj.www.auth.service.impl;

import com.gsj.www.auth.dao.AccountDAO;
import com.gsj.www.auth.dao.AccountPriorityRelationshipDAO;
import com.gsj.www.auth.dao.AccountRoleRelationshipDAO;
import com.gsj.www.auth.domain.*;
import com.gsj.www.auth.service.AccountService;
import com.gsj.www.common.util.DateProvider;
import com.gsj.www.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号管理sercie组件实现类
 */
@Service
public class AccountServiceImpl implements AccountService {
    /**
     * 账号管理DAO组件
     */
    @Autowired
    private AccountDAO accountDAO;
    /**
     * 账号和权限关联关系DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;
    /**
     * 账号和角色关联关系DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;
    /**
     * 日期辅助组件
     */
    private DateProvider dateProvider;

    /**
     * 分页查询账号
     * @param query 查询条件
     * @return 账号集合
     * @throws Exception
     */
    @Override
    public List<AccountDTO> listByPage(AccountQuery query) throws Exception {
        List<AccountDO> accounts = accountDAO.listByPage(query);
        List<AccountDTO> resultAccounts = ObjectUtils.convertList(accounts, AccountDTO.class);
        return resultAccounts;
    }

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     * @throws Exception
     */
    @Override
    public AccountDTO getById(Long id) throws Exception {
        AccountDO account = accountDAO.getById(id);
        AccountDTO resultAccount = account.clone(AccountDTO.class);

        List<AccountRoleRelationshipDO> roleRelationships = accountRoleRelationshipDAO.listByAccountId(id);
        List<AccountRoleRelationshipDTO> resultRoleRelations = ObjectUtils.convertList(roleRelationships, AccountRoleRelationshipDTO.class);
        resultAccount.setRoleRelatins(resultRoleRelations);

        List<AccountPriorityRelationshipDO> priorityRelations =
                accountPriorityRelationshipDAO.listByAccountId(id);
        List<AccountPriorityRelationshipDTO> resultPriorityRelations =
                ObjectUtils.convertList(priorityRelations, AccountPriorityRelationshipDTO.class);
        resultAccount.setPriorityRelations(resultPriorityRelations);

        return resultAccount;
    }

    /**
     * 新增账号
     * @param account 账号
     * @return 处理结果
     */
    @Override
    public void save(AccountDTO account) throws Exception {
        account.setGmtCreate(dateProvider.getCurrentTime());
        account.setGmtModified(dateProvider.getCurrentTime());
        Long accountId = accountDAO.save(account.clone(AccountDO.class));

        account.setId(accountId);

        saveRelations(account);
    }

    /**
     * 更新账号
     * @param account 账号
     * @return 处理结果
     */
    @Override
    public void update(AccountDTO account) throws Exception {
        account.setGmtModified(dateProvider.getCurrentTime());
        accountDAO.update(account.clone(AccountDO.class));

        accountRoleRelationshipDAO.removeByAccountId(account.getId());
        accountPriorityRelationshipDAO.removeByAccountId(account.getId());

        saveRelations(account);
    }

    /**
     * 新增关联关系
     * @param account 账号
     * @throws Exception
     */
    private void saveRelations(AccountDTO account) throws Exception {
        for(AccountRoleRelationshipDTO roleRelation : account.getRoleRelatins()) {
            roleRelation.setAccountId(account.getId());
            roleRelation.setGmtCreate(dateProvider.getCurrentTime());
            roleRelation.setGmtModified(dateProvider.getCurrentTime());
            accountRoleRelationshipDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));
        }

        for(AccountPriorityRelationshipDTO priorityRelation : account.getPriorityRelations()) {
            priorityRelation.setAccountId(account.getId());
            priorityRelation.setGmtCreate(dateProvider.getCurrentTime());
            priorityRelation.setGmtModified(dateProvider.getCurrentTime());
            accountPriorityRelationshipDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));
        }
    }

    /**
     * 删除账号
     * @param id 账号id
     * @return 处理结果
     */
    @Override
    public void remove(Long id) throws Exception {
        accountRoleRelationshipDAO.removeByAccountId(id);
        accountPriorityRelationshipDAO.removeByAccountId(id);
        accountDAO.remove(id);
    }
}
