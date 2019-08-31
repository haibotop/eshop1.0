package java.com.gsj.www.commodity.dao;

import com.gsj.www.commodity.dao.PropertyGroupDAO;
import com.gsj.www.commodity.domain.PropertyGroupDO;
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
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * 属性分组管理DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class PropertyGroupDAOTest {
    /**
     * 属性分组管理DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;
    /**
     * 日期辅助组件累
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试吸能属性分组
     */
    public void testSave() throws Exception{
        Long categoryId = 1L;
        PropertyGroupDO propertyGroupDO = createPropertyGroup(categoryId);
        assertNotNull(propertyGroupDO.getId());
        assertThat(propertyGroupDO.getId(), greaterThan(0L));
    }

    /**
     * 测试根据类目id查询属性分组
     * @throws Exception
     */
    @Test
    public void testListByCategoryId() throws Exception{
        Long categoryId = 1L;
        Integer count = 5;
        Map<Long, PropertyGroupDO> propertyGroupDOMap = createPropertyGroups(count, categoryId);

        List<PropertyGroupDO> resultPropertyGroups = propertyGroupDAO.listByCategoryId(categoryId);
        assertThat(resultPropertyGroups.size(), greaterThanOrEqualTo(propertyGroupDOMap.size()));
        for (PropertyGroupDO resultPropertyGroup : resultPropertyGroups) {
            PropertyGroupDO targetPropertyGroup = propertyGroupDOMap.get(resultPropertyGroup.getId());
            assertEquals(targetPropertyGroup, resultPropertyGroup);
        }
    }

    /**
     * 测试根据类目id删除属性分组
     * @throws Exception
     */
    @Test
    public void testRemoveByCategoryId() throws Exception{
        Long categoryId = 1L;
        Integer count = 5;
        createPropertyGroups(count, categoryId);
        propertyGroupDAO.removeByCategoryId(categoryId);
        List<PropertyGroupDO> resultPropertyGroups = propertyGroupDAO.listByCategoryId(categoryId);

        assertEquals(0, resultPropertyGroups.size());
    }

    /**
     * 创建属性分组集合
     * @param count 属性分组数量
     * @param categoryId 类目id
     * @return 属性分组集合
     */
    private Map<Long, PropertyGroupDO> createPropertyGroups(Integer count, Long categoryId) throws Exception{
        Map<Long, PropertyGroupDO> propertyGroupDOMap = new HashMap<>();
        for (int i = 0; i < count; i ++) {
            PropertyGroupDO propertyGroupDO = createPropertyGroup(categoryId);
            propertyGroupDOMap.put(propertyGroupDO.getId(), propertyGroupDO);
        }
        return propertyGroupDOMap;
    }

    /**
     * 创建属性分组
     * @param categoryId 类目id
     * @return 属性分组
     */
    private PropertyGroupDO createPropertyGroup(Long categoryId) throws Exception{
        PropertyGroupDO propertyGroupDO = new PropertyGroupDO();
        propertyGroupDO.setCategoryId(categoryId);
        propertyGroupDO.setGmtCreate(dateProvider.getCurrentTime());
        propertyGroupDO.setGmtModified(dateProvider.getCurrentTime());
        propertyGroupDO.setName("测试属性分组");
        propertyGroupDAO.save(propertyGroupDO);
        return propertyGroupDO;
    }
}
