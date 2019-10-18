package com.gsj.www.auth.dao;

import com.gsj.www.auth.domain.AccountDO;
import com.gsj.www.auth.domain.AccountQuery;

import java.util.List;

/**
 * 账号管理DAO组件接口
 */
public interface AccountDAO {
    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    List<AccountDO> listByPage(AccountQuery query);

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    AccountDO getById(Long id);

    /**
     * 新增账号
     * @param account 账号
     */
    Long save(AccountDO account);

    /**
     * 更新账号
     * @param account 账号
     */
    void update(AccountDO account);

    /**
     * 更新密码
     * @param accountDO 账号
     */
    void updatePassword(AccountDO accountDO);

    /**
     * 删除账号
     * @param id 账号id
     */
    void remove(Long id);
}
