package java.com.gsj.www.commodity.dao;

import com.gsj.www.commodity.constant.PropertyInputType;
import com.gsj.www.commodity.dao.PropertyDAO;
import com.gsj.www.commodity.domain.PropertyDO;
import com.gsj.www.commodity.domain.PropertyQuery;
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
 * 属性管理模块DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class PropertyDAOTest {
    /**
     * 属性管理模块DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;
    /**
     * 时间辅助工具类
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增商品属性
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        String inputVlues = "红色,蓝色,白色";
        String propertyDesc = "手机机身的颜色";
        String propertyName = "机身颜色";
        PropertyDO propertyDO = createProperty(inputVlues, propertyDesc, propertyName);
        assertNotNull(propertyDO.getId());
        assertThat(propertyDO.getId(), greaterThan(0L));
    }

    /**
     * 测试分页查询商品属性
     * @throws Exception
     */
    @Test
    public void testListPropertiesByPage() throws Exception{
        //模拟一个分页查询的场景
        //假如每页的数量是2条，我们来构造3页的数量，一共是5条，启宗五条都符合一个PropertyName的查询条件
        //我们来分页查询第2页
        PropertyDO propertyDO1 = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色1");
        PropertyDO propertyDO2 = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色2");
        PropertyDO propertyDO3 = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色3");
        PropertyDO propertyDO4 = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色4");
        PropertyDO propertyDO5 = createProperty(null, "手机前后摄像头的像素", "摄像头像素");
        PropertyDO propertyDO6 = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色5");

        Map<Long, PropertyDO> propertyDOMap = new HashMap<Long, PropertyDO>();
        propertyDOMap.put(propertyDO1.getId(), propertyDO1);
        propertyDOMap.put(propertyDO2.getId(), propertyDO2);
        propertyDOMap.put(propertyDO3.getId(), propertyDO3);
        propertyDOMap.put(propertyDO4.getId(), propertyDO4);
        propertyDOMap.put(propertyDO5.getId(), propertyDO5);
        propertyDOMap.put(propertyDO6.getId(), propertyDO6);

        Long propertyDO3Id = propertyDO3.getId();
        Long propertyDO4Id = propertyDO4.getId();

        // 我们可以来执行分页查询
        PropertyQuery propertyQuery = new PropertyQuery();
        propertyQuery.setOffset(2);
        propertyQuery.setSize(2);
        propertyQuery.setPropertyName("机身颜色");

        List<PropertyDO> propertyDOs = propertyDAO.listPropertiesByPage(propertyQuery);
        assertThat(propertyDOs.size(), greaterThan(0));

        for(PropertyDO propertyDO : propertyDOs) {
            PropertyDO targetPropertyDO = propertyDOMap.get(propertyDO.getId());
            assertEquals(targetPropertyDO, propertyDO);
            assertTrue(propertyDO.getId().equals(propertyDO3Id) ||
                    propertyDO.getId().equals(propertyDO4Id));
        }
    }

    /**
     * 测试根据id查询商品属性
     * @throws Exception
     */
    @Test
    public void testGetPropertyById() throws Exception {
        PropertyDO propertyDO = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色1");
        PropertyDO resultPropertyDO = propertyDAO.getPropertyById(propertyDO.getId());
        assertEquals(propertyDO, resultPropertyDO);
    }

    /**
     * 测试更新商品属性
     * @throws Exception
     */
    @Test
    public void testUpdateProperty() throws Exception {
        PropertyDO propertyDO = createProperty("红色,蓝色,白色", "手机机身的颜色", "机身颜色1");

        propertyDO.setGmtModified(dateProvider.getCurrentTime());
        propertyDO.setPropertyDesc("机身颜色");
        propertyDAO.updateProperty(propertyDO);

        PropertyDO resultPropertyDO = propertyDAO.getPropertyById(propertyDO.getId());

        assertEquals(propertyDO, resultPropertyDO);
    }

    /**
     * 创建一个属性对象
     * @param inputVlues 属性值
     * @param propertyDesc 属性描述
     * @param propertyName 属性名称
     * @return
     */
    private PropertyDO createProperty(String inputVlues, String propertyDesc, String propertyName) throws Exception{
        PropertyDO property = new PropertyDO();
        property.setInputType(PropertyInputType.MULTIPUT_CHOICE);
        property.setInputValues(inputVlues);
        property.setPropertyDesc(propertyDesc);
        property.setPropertyName(propertyName);
        property.setGmtCreate(dateProvider.getCurrentTime());
        property.setGmtModified(dateProvider.getCurrentTime());
        propertyDAO.saveProperty(property);
        return property;
    }
}
