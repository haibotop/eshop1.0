package com.gsj.www.commodity.service.impl;

import com.gsj.www.commodity.dao.CategoryDAO;
import com.gsj.www.commodity.dao.CategoryPropertyRelationshipDAO;
import com.gsj.www.commodity.dao.PropertyGroupDAO;
import com.gsj.www.commodity.dao.PropertyGroupRelationshipDAO;
import com.gsj.www.commodity.domain.PropertyGroupDO;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类目删除操作
 */
@Component
@Scope("prototype")
public class CategotyRemoveOperation extends AbstractCategoryOperation<Boolean> {
    /**
     * 类目与属性关系管理DAO组件
     */
    private CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;
    /**
     * 属性分组管理DAO组件
     */
    private PropertyGroupDAO propertyGroupDAO;
    /**
     * 属性分组与属性关系管理DAO组件
     */
    private PropertyGroupRelationshipDAO propertyGroupRelationshipDAO;

    /**
     * 构造函数
     *
     * @param categoryDAO 类目管理DAO组件
     */
    public CategotyRemoveOperation(CategoryDAO categoryDAO,
                                   CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO,
                                   PropertyGroupDAO propertyGroupDAO,
                                   PropertyGroupRelationshipDAO propertyGroupRelationshipDAO
    ) {
        super(categoryDAO);
        this.categoryPropertyRelationshipDAO = categoryPropertyRelationshipDAO;
        this.propertyGroupDAO = propertyGroupDAO;
        this.propertyGroupRelationshipDAO = propertyGroupRelationshipDAO;
    }

    /**
     * 执行实际的操作
     * @param category 类目
     * @throws Exception
     */
    @Override
    protected void doRealExecute(Category category) throws Exception {
        removePropertyRelation(category);
        removePropertyGroup(category);
        removeCategory(category);
    }

    /**
     * 删除类目与属性关联关系
     * @param category
     * @throws Exception
     */
    private void removePropertyRelation(Category category) throws Exception{
        categoryPropertyRelationshipDAO.removeByCategoryId(category.getCategoryId());
    }

    /**
     * 删除类目的属性分组
     * @param category 类目
     * @throws Exception
     */
    private void removePropertyGroup(Category category) throws Exception{
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(category.getCategoryId());
        for (PropertyGroupDO propertyGroup : propertyGroups) {
            propertyGroupRelationshipDAO.removeByPropertyGroupId(propertyGroup.getId());
        }
        propertyGroupDAO.removeByCategoryId(category.getCategoryId());
    }

    /**
     * 删除类目
     * @param category 类目
     * @throws Exception
     */
    private void removeCategory(Category category) throws Exception{
        categoryDAO.remove(category.getCategoryId());
    }

    /**
     * 获取操作的执行结果
     * @return
     * @throws Exception
     */
    @Override
    protected Boolean getResult() throws Exception {
        return true;
    }
}
