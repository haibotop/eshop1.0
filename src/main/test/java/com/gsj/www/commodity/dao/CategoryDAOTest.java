package java.com.gsj.www.commodity.dao;

import com.gsj.www.commodity.constant.CategoryLeaf;
import com.gsj.www.commodity.dao.CategoryDAO;
import com.gsj.www.commodity.domain.CategoryDO;
import com.gsj.www.commodity.service.impl.Category;
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
 * 类目管理DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class CategoryDAOTest {
    /**
     * 类目管理DAO组件
     */
    @Autowired
    private CategoryDAO categoryDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增类目
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        CategoryDO categoryDO = createCategory();
        assertNotNull(categoryDO.getId());
        assertThat(categoryDO.getId(), greaterThan(0L));
    }

    /**
     * 测试查询根类目
     * @throws Exception
     */
    @Test
    public void testListRoots() throws Exception{
        Integer categoryCount = 5;
        Map<Long , CategoryDO> categoryDOMap = createCategories(categoryCount);
        List<CategoryDO> resultCategories = categoryDAO.listRoots();

        compareCategoties(categoryDOMap, resultCategories);
    }

    /**
     * 测试查询子类目
     * @throws Exception
     */
    @Test
    public void testListChildren() throws Exception{
        Integer categoryCount = 5;
        Long parentId = 1L;
        Map<Long, CategoryDO> categoryDOMap = createCategories(categoryCount, parentId);
        List<CategoryDO> resultCategories = categoryDAO.listChildren(parentId);
        compareCategoties(categoryDOMap, resultCategories);
    }

    /**
     * 测试根据id查询类目
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception{
        CategoryDO category = createCategory();
        CategoryDO resultCategory = categoryDAO.getById(category.getId());
        assertEquals(category, resultCategory);
    }

    /**
     * 测试更新类目
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception{
        CategoryDO category = createCategory();

        category.setDescription("修改后的测试类目");
        category.setGmtModified((dateProvider.getCurrentTime()));
        categoryDAO.update(category);

        CategoryDO resultCategory = categoryDAO.getById(category.getId());

        assertEquals(category, resultCategory);
    }

    /**
     * 测试删除类目
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception{
        CategoryDO categoryDO = createCategory();
        categoryDAO.remove(categoryDO.getId());
        CategoryDO resultCategory = categoryDAO.getById(categoryDO.getId());
        assertNull(resultCategory);
    }

    /**
     * 创建根类目
     * @return 根类目
     * @throws Exception
     */
    private CategoryDO createCategory() throws Exception{
        Long parentId = null;
        return createCategory(parentId);
    }

    /**
     * 创建类目
     * @return 类目
     * @throws Exception
     */
    private CategoryDO createCategory(Long parentId) throws Exception {
        CategoryDO category = new CategoryDO();
        category.setIsLeaf(CategoryLeaf.NO);
        category.setDescription("测试类目");
        category.setGmtCreate(dateProvider.getCurrentTime());
        category.setGmtModified(dateProvider.getCurrentTime());
        category.setName("测试类目");
        category.setParentId(parentId);

        categoryDAO.save(category);

        return category;
    }

    /**
     * 创建类目集合
     * @param categoryCount 类目数量
     * @return 类目集合
     */
    private Map<Long, CategoryDO> createCategories(Integer categoryCount) throws Exception {
        Long parentid = null;
        return createCategories(categoryCount, parentid);
    }

    /**
     * 创建类目集合
     * @param categoryCount 类目数量
     * @param parentId 父类目id
     * @return
     */
    private Map<Long, CategoryDO> createCategories(Integer categoryCount, Long parentId) throws Exception{
        Map<Long, CategoryDO> categoryDOMap = new HashMap<>();
        for(int i = 0; i < categoryCount; i ++){
            CategoryDO category = createCategory(parentId);
            categoryDOMap.put(category.getId(), category);
        }
        return categoryDOMap;
    }

    /**
     * 比较两个类目集合
     * @param categoryDOMap 目标类目集合
     * @param resultCategories 结果类目集合
     */
    private void compareCategoties(Map<Long, CategoryDO> categoryDOMap, List<CategoryDO> resultCategories)  throws Exception{
        assertThat(resultCategories.size(), greaterThanOrEqualTo(categoryDOMap.size()));

        for (CategoryDO resultCategory : resultCategories) {
            CategoryDO category = categoryDOMap.get(resultCategory.getId());
            if(category == null){
                continue;
            }
            assertEquals(category, resultCategory);
        }
    }




}
