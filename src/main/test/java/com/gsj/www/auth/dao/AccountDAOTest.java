package java.com.gsj.www.auth.dao;

import com.gsj.www.auth.dao.AccountDAO;
import com.gsj.www.auth.domain.AccountDO;
import com.gsj.www.auth.domain.AccountQuery;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * 账号管理DAO组件的单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class AccountDAOTest {
    /**
     * 账号管理DAO组件
     */
    @Autowired
    private AccountDAO accountDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增账号
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        AccountDO account = createAccount();
        assertNotNull(account.getId());
        assertThat(account.getId(), greaterThan(0L));
    }

    /**
     * 测试分页查询账号
     * @throws Exception
     */
    @Test
    public void testListByPage() throws Exception{
        int count = 30;
        Map<Long, AccountDO> accountDOMap = createAccounts(count);

        Integer offset = 20;
        Integer size = 10;
        AccountQuery query = new AccountQuery();
        query.setOffset(offset);
        query.setSize(size);
        query.setUsername("zhangsan");
        query.setName("张三");

        List<AccountDO> resultAccounts = accountDAO.listByPage(query);

        assertEquals((int)size, resultAccounts.size());
        compareAccounts(accountDOMap, resultAccounts);
    }

    /**
     *  测试根据id查询账号
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception{
        AccountDO accountDO = createAccount();
        AccountDO resultAccount = accountDAO.getById(accountDO.getId());
        assertEquals(accountDO, resultAccount);
    }

    /**
     * 测试更新账号
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception{
        AccountDO accountDO = createAccount();

        accountDO.setRemark("修改后的测试账号备注");
        accountDO.setGmtModified(dateProvider.getCurrentTime());
        accountDAO.update(accountDO);

        AccountDO resultAccount = accountDAO.getById(accountDO.getId());
        assertEquals(accountDO, resultAccount);
    }

    /**
     * 测试更新密码
     * @throws Exception
     */
    @Test
    public void testUpdatePassword() throws Exception{
        AccountDO accountDO = createAccount();
        accountDO.setPassword("87654321");
        accountDO.setGmtModified(dateProvider.getCurrentTime());
        accountDAO.updatePassword(accountDO);

        AccountDO resultAccount = accountDAO.getById(accountDO.getId());
        assertEquals(accountDO, resultAccount);
    }

    /**
     * 测试删除账号
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception{
        AccountDO accountDO = createAccount();
        accountDAO.remove(accountDO.getId());
        AccountDO resultAccount = accountDAO.getById(accountDO.getId());
        assertNull(resultAccount);
    }

    /**
     * 比较两个账号集合
     * @param targetAccountMap
     * @param resultAccounts
     */
    private void compareAccounts(Map<Long, AccountDO> targetAccountMap, List<AccountDO> resultAccounts) throws Exception{
        for (AccountDO resultAccount : resultAccounts) {
            AccountDO targetAccount = targetAccountMap.get(resultAccount.getId());
            assertEquals(targetAccount, resultAccount);
        }
    }

    /**
     * 创建账号集合
     * @param count
     * @return
     */
    private Map<Long, AccountDO> createAccounts(int count) throws Exception{
        Map<Long, AccountDO> accountDOMap = new HashMap<>();
        AccountDO account = null;
        for(int i = 0; i < count; i++){
            account = createAccount();
            accountDOMap.put(account.getId(), account);
        }

        return accountDOMap;
    }

    /**
     * 创建账号
     * @return
     */
    private AccountDO createAccount() throws Exception{
        AccountDO accountDO = new AccountDO();
        accountDO.setUsername("zhangsan");
        accountDO.setPassword("12345678");
        accountDO.setName("张三");
        accountDO.setRemark("测试账号");
        accountDO.setGmtCreate(dateProvider.getCurrentTime());
        accountDO.setGmtModified(dateProvider.getCurrentTime());

        accountDAO.save(accountDO);

        return accountDO;
    }
}
