package java.com.gsj.www.auth.dao;

import com.gsj.www.auth.dao.AccountRoleRelationshipDAO;
import com.gsj.www.auth.domain.AccountRoleRelationshipDO;
import com.gsj.www.common.util.DateProvider;
import org.apache.ibatis.annotations.Param;
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
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * 账号和角色关系管理模块的DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class AccountRoleRelationshipDAOTest {
    /**
     * 账号和角色关系管理模块的DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增账号和角色的关联关系
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        Long accountId = 1L;
        Long roleId = 1L;
        AccountRoleRelationshipDO accountRoleRelationshipDO = createAccountRoleRelationshipDO(accountId, roleId);

        assertNotNull(accountRoleRelationshipDO.getId());
        assertThat(accountRoleRelationshipDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据角色id查询记录数
     * @throws Exception
     */
    @Test
    public void testCountByRoleId() throws Exception{
        Long roleId = 1L;
        Long accountId1 = 1L;
        createAccountRoleRelationshipDO(accountId1, roleId);

        Long accountId2 = 2L;
        createAccountRoleRelationshipDO(accountId2, roleId);

        Long resultCount = accountRoleRelationshipDAO.countByRoleId(roleId);

        assertEquals(2L, resultCount.longValue());
    }

    /**
     * 测试根据账号id查询账号和角色的关联关系
     */
    @Test
    public void testListByAccountId() throws Exception{
        Long accountId = 1L;
        int count = 20;
        Map<Long, AccountRoleRelationshipDO> relationshipDOMap = createRelations(accountId, count);

        List<AccountRoleRelationshipDO> resultRelations = accountRoleRelationshipDAO.listByAccountId(accountId);

        compareRelatons(relationshipDOMap, resultRelations);
    }

    /**
     * 测试根据账号id删除账号和角色的关联关系
     * @throws Exception
     */
    @Test
    public void testRemoveByAccountId() throws Exception{
        Long accountId = 1L;
        int count = 20;
        createRelations(accountId,count);

        accountRoleRelationshipDAO.removeByAccountId(accountId);
        List<AccountRoleRelationshipDO> resultRelations = accountRoleRelationshipDAO.listByAccountId(accountId);

        assertEquals(0, resultRelations.size());
    }

    /**
     * 比较两个关联关系集合
     * @param relationshipDOMap
     * @param resultRelations
     */
    private void compareRelatons(Map<Long, AccountRoleRelationshipDO> relationshipDOMap, List<AccountRoleRelationshipDO> resultRelations) throws Exception{
        assertThat(resultRelations.size(), greaterThanOrEqualTo(relationshipDOMap.size()));

        for (AccountRoleRelationshipDO resultRelation : resultRelations) {
            AccountRoleRelationshipDO targetReletion = relationshipDOMap.get(resultRelation.getId());
            if(targetReletion == null){
                continue;
            }
            assertEquals(targetReletion, resultRelation);
        }
    }

    /**
     * 创建账号和角色关联关系的集合
     * @param accountId
     * @param count
     * @return
     */
    private Map<Long, AccountRoleRelationshipDO> createRelations(Long accountId, int count) throws Exception{
        Map<Long, AccountRoleRelationshipDO> relationshipDOMap = new HashMap<>();

        for(int i = 0; i < count; i++){
            AccountRoleRelationshipDO relationshipDO = createAccountRoleRelationshipDO(accountId, (long)i);
            relationshipDOMap.put(relationshipDO.getId(), relationshipDO);
        }

        return relationshipDOMap;
    }

    /**
     * 创建账号和角色关系DO对象
     * @param accountId
     * @param roleId
     * @return
     */
    private AccountRoleRelationshipDO createAccountRoleRelationshipDO(Long accountId, Long roleId) throws Exception{
        AccountRoleRelationshipDO accountRoleRelationshipDO = new AccountRoleRelationshipDO();
        accountRoleRelationshipDO.setAccountId(accountId);
        accountRoleRelationshipDO.setRoleId(roleId);
        accountRoleRelationshipDO.setGmtCreate(dateProvider.getCurrentTime());
        accountRoleRelationshipDO.setGmtModified(dateProvider.getCurrentTime());

        return accountRoleRelationshipDO;
    }


}
