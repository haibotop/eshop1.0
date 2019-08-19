package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.AccountDAO;
import com.gsj.www.auth.domain.AccountDO;
import com.gsj.www.auth.domain.AccountQuery;
import com.gsj.www.auth.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账号管理DAO组件实现类
 */
@Repository
public class AccountDAOImpl implements AccountDAO {
    /**
     * 账号管理mapper组件
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    @Override
    public List<AccountDO> listByPage(AccountQuery query) {
        return accountMapper.listByPage(query);
    }

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    @Override
    public AccountDO getById(Long id) {
        return accountMapper.getById(id);
    }

    /**
     * 新增账号
     * @param account 账号
     */
    @Override
    public Long save(AccountDO account) {
        accountMapper.save(account);
        return account.getId();
    }

    /**
     * 更新账号
     * @param account 账号
     */
    @Override
    public void update(AccountDO account) {
        accountMapper.update(account);
    }

    /**
     * 删除账号
     * @param id 账号id
     */
    @Override
    public void remove(Long id) {
        accountMapper.remove(id);
    }
}
