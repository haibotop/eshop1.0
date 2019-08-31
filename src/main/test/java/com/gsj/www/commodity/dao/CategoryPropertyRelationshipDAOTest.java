package java.com.gsj.www.commodity.dao;

import com.gsj.www.commodity.constant.PropertyRequired;
import com.gsj.www.commodity.dao.CategoryPropertyRelationshipDAO;
import com.gsj.www.commodity.domain.CategoryPropertyRelationshipDO;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
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
 * 类目与属性关系管理DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class CategoryPropertyRelationshipDAOTest {
    /**
     * 类目与属性关系管理DAO组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO relationshipDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增类目与属性关系
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        Long categoryId = 1L;
        CategoryPropertyRelationshipDO relation = createRelation(categoryId);
        assertNotNull(relation.getId());
        assertThat(relation.getId(), greaterThan(0L));
    }

    /**
     * 测试根据类目id查询类目与属性的关联关系
     * @throws Exception
     */
    @Test
    public void testListByCategoryId() throws Exception{
        Long categoryId = 1L;
        Integer relationCount = 5;
        Map<Long, CategoryPropertyRelationshipDO> relationshipDOMap = createRelations(relationCount, categoryId);
        List<CategoryPropertyRelationshipDO> resultRelations = relationshipDAO.listByCategoryId(categoryId);

        compareRelations(relationshipDOMap, resultRelations);
    }

    /**
     * 测试根据类目id删除类目与属性的关联关系
     * @throws Exception
     */
    @Test
    public void testRemoveByCategoryId() throws Exception{
        Integer relationCount = 5;
        Long categoryId = 1L;
        createRelations(relationCount, categoryId);
        relationshipDAO.removeByCategoryId(categoryId);
        List<CategoryPropertyRelationshipDO> resultRelations = relationshipDAO.listByCategoryId(categoryId);
        assertEquals(0, resultRelations.size());
    }

    /**
     * 比较两个类目与属性关联关系集合
     * @param targetRelationMap 类目与属性关联关系map集合
     * @param resultRelations 类目与属性关联关系list集合
     */
    private void compareRelations(Map<Long, CategoryPropertyRelationshipDO> targetRelationMap, List<CategoryPropertyRelationshipDO> resultRelations) throws Exception{
        assertThat(resultRelations.size(), greaterThanOrEqualTo(targetRelationMap.size()));

        for (CategoryPropertyRelationshipDO resultRelation : resultRelations) {
            CategoryPropertyRelationshipDO targetRelation = targetRelationMap.get(resultRelation.getId());
            assertEquals(targetRelation, resultRelation);
        }
    }

    /**
     * 创建类目与属性关联关系集合
     * @param relationCount 关联关系数量
     * @param categoryId 类目id
     * @return
     */
    private Map<Long, CategoryPropertyRelationshipDO> createRelations(Integer relationCount, Long categoryId) throws Exception{
        Map<Long, CategoryPropertyRelationshipDO> relationshipDOMap = new HashMap<>();
        for (int i = 0; i < relationCount; i ++){
            CategoryPropertyRelationshipDO relationshipDO = createRelation(categoryId);
            relationshipDOMap.put(relationshipDO.getId(), relationshipDO);
        }
        return relationshipDOMap;
    }

    /**
     * 创建类目与属性的关联关系
     * @param categoryId 类目id
     * @return
     */
    private CategoryPropertyRelationshipDO createRelation(Long categoryId) throws Exception{
        Random random = new Random();

        CategoryPropertyRelationshipDO relation = new CategoryPropertyRelationshipDO();
        relation.setCategoryId(categoryId);
        relation.setGmtCreate(dateProvider.getCurrentTime());
        relation.setGmtModified(dateProvider.getCurrentTime());
        relation.setPropertyId(random.nextLong());
        relation.setPropertyTypes("测试类型");
        relation.setRequired(PropertyRequired.YES);
        return relation;
    }
}
