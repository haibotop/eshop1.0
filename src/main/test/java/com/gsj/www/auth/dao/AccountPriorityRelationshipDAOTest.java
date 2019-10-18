package java.com.gsj.www.auth.dao;

import com.gsj.www.auth.dao.AccountPriorityRelationshipDAO;
import com.gsj.www.auth.domain.AccountPriorityRelationshipDO;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 账号和权限关系管理模块的DAO组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 06 - 21 22:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class AccountPriorityRelationshipDAOTest {
    /**
     * 账号和权限关系管理模块的DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增账号和权限的关联关系
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Long accountId = 1L;
        Long priorityId = 1L;
        AccountPriorityRelationshipDO accountPriorityRelationshipDO =
                createAccountPriorityRelationshipDO(accountId, priorityId);
        assertNotNull(accountPriorityRelationshipDO.getId());
        assertThat(accountPriorityRelationshipDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据权限id查询记录数
     * @throws Exception
     */
    @Test
    public void testCountByPriorityId() throws Exception {
        Long priorityId = 1L;

        Long accountId1 = 1L;
        createAccountPriorityRelationshipDO(accountId1, priorityId);

        Long accountId2 = 2L;
        createAccountPriorityRelationshipDO(accountId2, priorityId);

        Long resultCount = accountPriorityRelationshipDAO.getCountByPriorityId(priorityId);

        assertEquals(2L, resultCount.longValue());
    }

    /**
     * 测试根据账号id查询账号和权限的关联关系
     * @throws Exception
     */
    @Test
    public void testListByAccountId() throws Exception{
        Long accountId = 1L;
        int count = 20;
        Map<Long, AccountPriorityRelationshipDO> relationMap = createRelations(accountId, count);

        List<AccountPriorityRelationshipDO> resultRelations = accountPriorityRelationshipDAO.listByAccountId(accountId);

        compareRelations(relationMap, resultRelations);
    }

    /**
     * 测试根据账号id删除账号和权限的关联关系
     * @throws Exception
     */
    @Test
    public void testRemoveByAccountId() throws Exception{
        Long accountId = 1L;
        int count = 20;
        createRelations(accountId, count);

        accountPriorityRelationshipDAO.removeByAccountId(accountId);

        List<AccountPriorityRelationshipDO> resultRelations = accountPriorityRelationshipDAO.listByAccountId(accountId);

        assertEquals(0, resultRelations.size());
    }

    /**
     * 比较两个关联关系集合
     * @param relationMap
     * @param resultRelations
     */
    private void compareRelations(Map<Long, AccountPriorityRelationshipDO> relationMap, List<AccountPriorityRelationshipDO> resultRelations) throws Exception{
        assertThat(resultRelations.size(), greaterThan(relationMap.size()));
        for (AccountPriorityRelationshipDO resultRelation : resultRelations) {
            AccountPriorityRelationshipDO targetRelation = relationMap.get(resultRelation.getId());
            if(targetRelation == null){
                continue;
            }
            assertEquals(targetRelation, resultRelation);
        }

    }

    /**
     * 创建账号和权限关联关系的集合
     * @param accountId
     * @param count
     * @return
     */
    private Map<Long, AccountPriorityRelationshipDO> createRelations(Long accountId, int count) throws Exception{
        Map<Long, AccountPriorityRelationshipDO> relationshipDOMap = new HashMap<>();

        for(int i = 0; i < count; i++){
            AccountPriorityRelationshipDO relationshipDO = createAccountPriorityRelationshipDO(accountId, (long)i);
            relationshipDOMap.put(relationshipDO.getId(), relationshipDO);
        }

        return relationshipDOMap;
    }

    /**
     * 创建账号和权限关系DO对象
     * @return 账号和权限关系DO对象
     * @throws Exception
     */
    private AccountPriorityRelationshipDO createAccountPriorityRelationshipDO(
            Long accountId, Long priorityId) throws Exception {
        AccountPriorityRelationshipDO accountPriorityRelationshipDO =
                new AccountPriorityRelationshipDO();
        accountPriorityRelationshipDO.setAccountId(accountId);
        accountPriorityRelationshipDO.setPriorityId(priorityId);
        accountPriorityRelationshipDO.setGmtCreate(dateProvider.getCurrentTime());
        accountPriorityRelationshipDO.setGmtModified(dateProvider.getCurrentTime());

        accountPriorityRelationshipDAO.save(accountPriorityRelationshipDO);

        return accountPriorityRelationshipDO;
    }
}
