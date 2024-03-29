package java.com.gsj.www.auth.dao;

import com.gsj.www.auth.dao.RoleDAO;
import com.gsj.www.auth.domain.RoleDO;
import com.gsj.www.auth.domain.RoleQuery;
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
import static org.junit.Assert.*;

/**
 * 角色管理模块DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class RoleDAOTest {
    /**
     * 角色管理模块DAO组件
     */
    @Autowired
    private RoleDAO roleDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增角色
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        assertNotNull(role.getId());
        assertThat(role.getId(), greaterThan(0L));
    }

    /**
     * 测试分页查询角色
     * @throws Exception
     */
    @Test
    public void testListByPage() throws Exception {
        // 准备参数
        String namePrefix = "测试角色";
        String codePrefix = "TEST_ROLE";
        String otherName = "别的角色";
        String otherCode = "OTHER_ROLE";
        Integer offset = 2;
        Integer size = 2;

        // 构造6个角色出来
        // 其中5个角色都是以“测试角色”打头
        Map<Long, RoleDO> roleMap = new HashMap<Long, RoleDO>();

        RoleDO role = null;

        for(int i = 0; i < 5; i++) {
            role = createRole(namePrefix + i, codePrefix + i);
            roleMap.put(role.getId(), role);
        }

        role = createRole(otherName, otherCode);

        // 执行分页查询的方法逻辑
        RoleQuery query = new RoleQuery();
        query.setOffset(offset);
        query.setSize(size);
        query.setName(namePrefix);
        query.setCode(codePrefix);

        List<RoleDO> resultRoles = roleDAO.listByPage(query);

        // 执行断言
        assertEquals((int)size, resultRoles.size());

        for(RoleDO resultRole : resultRoles) {
            assertEquals(roleMap.get(resultRole.getId()), resultRole);
        }
    }

    /**
     * 测试根据id查询角色DO对象
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        RoleDO resultRole = roleDAO.getById(role.getId());
        assertEquals(role, resultRole);
    }

    /**
     * 测试更新角色
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");

        role.setName(role.getName() + "_修改");
        role.setCode(role.getCode() + "_modified");
        roleDAO.update(role);

        RoleDO resultRole = roleDAO.getById(role.getId());

        assertEquals(role, resultRole);
    }

    /**
     * 测试删除角色
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception {
        RoleDO role = createRole("测试角色", "TEST_CODE");
        roleDAO.remove(role.getId());
        RoleDO resultRole = roleDAO.getById(role.getId());
        assertNull(resultRole);
    }

    /**
     * 创建角色
     * @throws Exception
     */
    private RoleDO createRole(String name, String code) throws Exception {
        RoleDO role = new RoleDO();
        role.setName(name);
        role.setCode(code);
        role.setRemark(name);
        role.setGmtCreate(dateProvider.getCurrentTime());
        role.setGmtModified(dateProvider.getCurrentTime());

        roleDAO.save(role);

        return role;
    }

}
