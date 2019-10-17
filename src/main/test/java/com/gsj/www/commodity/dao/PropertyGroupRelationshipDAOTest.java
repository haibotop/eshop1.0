package java.com.gsj.www.commodity.dao;

import com.gsj.www.commodity.constant.PropertyRequired;
import com.gsj.www.commodity.dao.PropertyGroupRelationshipDAO;
import com.gsj.www.commodity.domain.PropertyGroupRelationshipDO;
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
import java.util.Random;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * 属性分组与属性关系管理DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class PropertyGroupRelationshipDAOTest {
    /**
     * 属性分组与属性关联关系管理DAO组件
     */
    @Autowired
    private PropertyGroupRelationshipDAO relationshipDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增属性分组与属性关系
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        Long propertyGroupId = 1L;
        PropertyGroupRelationshipDO relation = createRelation(propertyGroupId);
        assertNotNull(relation.getId());
        assertThat(relation.getId(), greaterThan(0L));
    }

    /**
     * 测试根据属性分组id查询属性分组与属性的关联关系
     * @throws Exception
     */
    @Test
    public void testListByPropertyGroupId() throws Exception{
        Long propertyGroupId = 1L;
        Integer relationCount = 5;
        Map<Long, PropertyGroupRelationshipDO> relationshipDOMap = createRelations(relationCount, propertyGroupId);
        List<PropertyGroupRelationshipDO> resultRelations = relationshipDAO.listByPropertyGroupId(propertyGroupId);
        compareRelations(relationshipDOMap, resultRelations);
    }

    /**
     * 测试根据属性分组id删除属性分组与属性的关联关系
     * @throws Exception
     */
    @Test
    public void testRemoveByPropertyGroupId() throws Exception{
        Integer relationCount = 5;
        Long propertyGroupId = 1L;
        createRelations(relationCount, propertyGroupId);
        relationshipDAO.removeByPropertyGroupId(propertyGroupId);
        List<PropertyGroupRelationshipDO> resultRelations = relationshipDAO.listByPropertyGroupId(propertyGroupId);
        assertEquals(0, resultRelations.size());
    }

    /**
     * 比较两个属性分组与属性关联关系集合
     * @param targetRelationMap 属性分组与属性关联关系map集合
     * @param resultRelations 属性分组与属性关联关系list集合
     * @throws Exception
     */
    private void compareRelations(Map<Long, PropertyGroupRelationshipDO> targetRelationMap, List<PropertyGroupRelationshipDO> resultRelations) throws Exception{
        assertThat(resultRelations.size(), greaterThanOrEqualTo(targetRelationMap.size()));
        for (PropertyGroupRelationshipDO resultRelation : resultRelations) {
            PropertyGroupRelationshipDO targetRelation = targetRelationMap.get(resultRelation.getId());
            assertEquals(targetRelation, resultRelation);
        }
    }

    /**
     * 创建属性分组与属性关联关系集合
     * @param relationCount 属性分组与属性关联数量
     * @param propertyGroupId 属性分组与属性关联关系id
     * @return
     */
    private Map<Long, PropertyGroupRelationshipDO> createRelations(Integer relationCount, Long propertyGroupId) throws Exception{
        Map<Long, PropertyGroupRelationshipDO> relationshipDOMap = new HashMap<>();
        for(int i = 0; i < relationCount; i ++){
            PropertyGroupRelationshipDO relationshipDO = createRelation(propertyGroupId);
            relationshipDOMap.put(relationshipDO.getId(), relationshipDO);
        }
        return relationshipDOMap;
    }

    /**
     * 创建属性分组与属性关联关系
     * @param propertyGroupId 分组与属性关联关系id
     * @return
     */
    private PropertyGroupRelationshipDO createRelation(Long propertyGroupId) throws Exception {
        Random random = new Random();
        PropertyGroupRelationshipDO relation = new PropertyGroupRelationshipDO();
        relation.setPropertyGroupId(propertyGroupId);
        relation.setGmtCreate(dateProvider.getCurrentTime());
        relation.setGmtModified(dateProvider.getCurrentTime());
        relation.setPropertyId(random.nextLong());
        relation.setPropertyTypes("测试类型");
        relation.setRequired(PropertyRequired.YES);
        relationshipDAO.save(relation);
        return relation;
    }
}
