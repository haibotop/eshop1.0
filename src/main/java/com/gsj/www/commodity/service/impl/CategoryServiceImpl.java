package com.gsj.www.commodity.service.impl;

import com.gsj.www.commodity.dao.*;
import com.gsj.www.commodity.domain.*;
import com.gsj.www.commodity.service.CategoryService;
import com.gsj.www.common.util.DateProvider;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 类目管理DAO组件
     */
    @Autowired
    private CategoryDAO categoryDAO;
    /**
     * 类目属性关系DAO组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;
    /**
     * 属性分组DAO组件
     */
    @Autowired
    private PropertyGroupDAO propertyGroupDAO;
    /**
     * 属性分组与属性关系DAO组件
     */
    @Autowired
    private PropertyGroupRelationshipDAO propertyGroupRelationshipDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 属性管理模块DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * 查询根类目
     * @return 根类目集合
     */
    @Override
    public List<CategoryDTO> listRoots() {
        try{
            List<CategoryDO> categories = categoryDAO.listRoots();
            List<CategoryDTO> resultCategories = ObjectUtils.convertList(categories,CategoryDTO.class);

            return resultCategories;
        }catch (Exception e) {
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     */
    @Override
    public List<CategoryDTO> listChildren(Long id) {
        try {
            List<CategoryDO> categories = categoryDAO.listChildren(id);
            List<CategoryDTO> resultCategories = ObjectUtils.convertList(categories,CategoryDTO.class);
            return resultCategories;
        }catch (Exception e) {
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 新增类目
     * @param category 类目
     * @return 处理结果
     */
    @Override
    public Boolean save(CategoryDTO category) {
        try{
            //保存类目的基本信息
            saveCategory(category);
            //保存类目与属性之间的关联关系
            saveCategoryPropertyRelations(category);
            //保存属性分组
            savePropertyGroup(category);
            return true;
        }catch (Exception e) {
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 保存类目的基本信息
     * @param category 类目
     * @throws Exception
     */
    private void saveCategory(CategoryDTO category) throws Exception{
        category.setGmtCreate(dateProvider.getCurrentTime());
        category.setGmtModified(dateProvider.getCurrentTime());
        Long categoryId = categoryDAO.save(category.clone(CategoryDO.class));
        category.setId(categoryId);
    }

    /**
     * 保存类目与属性之间的关联关系
     * @param category 类目
     * @throws Exception
     */
    private void saveCategoryPropertyRelations(CategoryDTO category) throws Exception{
        if(category.getPropertyRelations() == null ||
                category.getPropertyRelations().size() == 0){
            return;
        }

        for (CategoryPropertyRelationshipDTO relation : category.getPropertyRelations()) {
            relation.setCategoryId(category.getId());
            relation.setGmtCreate(dateProvider.getCurrentTime());
            relation.setGmtModified(dateProvider.getCurrentTime());

            categoryPropertyRelationshipDAO.save(relation.clone(CategoryPropertyRelationshipDO.class));
        }
    }

    /**
     * 保存属性分组
     * @param category 类目
     * @throws Exception
     */
    private void savePropertyGroup(CategoryDTO category) throws Exception {
        if(category.getPropertyGroups() == null ||
                category.getPropertyGroups().size() == 0) {
            return;
        }

        for(PropertyGroupDTO group : category.getPropertyGroups()) {
            group.setCategoryId(category.getId());
            group.setGmtCreate(dateProvider.getCurrentTime());
            group.setGmtModified(dateProvider.getCurrentTime());

            Long groupId = propertyGroupDAO.save(group.clone(PropertyGroupDO.class));
            group.setId(groupId);

            savePropertyGroupRelations(group);
        }
    }

    /**
     * 保存属性分组与属性的关联关系
     * @param group 属性分组
     * @throws Exception
     */
    private void savePropertyGroupRelations(PropertyGroupDTO group) throws Exception {
        if(group.getRelations() == null || group.getRelations().size() == 0) {
            return;
        }

        for(PropertyGroupRelationshipDTO relation : group.getRelations()) {
            relation.setPropertyGroupId(group.getId());
            relation.setGmtCreate(dateProvider.getCurrentTime());
            relation.setGmtModified(dateProvider.getCurrentTime());

            propertyGroupRelationshipDAO.save(relation.clone(
                    PropertyGroupRelationshipDO.class));
        }
    }

    /**
     * 根据id查询类目
     * @param id 类目id
     * @return 类目
     */
    public CategoryDTO getById(Long id){
        try {
            //查询类目基本信息
            CategoryDTO category = categoryDAO.getById(id).clone(CategoryDTO.class);

            //查询类目与属性的关联关系
            List<CategoryPropertyRelationshipDO> relations = categoryPropertyRelationshipDAO.listByCategoryId(id);

            category.setPropertyRelations(ObjectUtils.convertList(relations, CategoryPropertyRelationshipDTO.class));

            //查询类目挂链的属性
            List<PropertyDO> properties = new ArrayList<>();
            for (CategoryPropertyRelationshipDO relation : relations) {
                properties.add(propertyDAO.getPropertyById(relation.getPropertyId()));
            }

            category.setProperties(ObjectUtils.convertList(properties,PropertyDTO.class));

            //查询类目关联的属性分组
            List<PropertyGroupDTO> propertyGroups = getPropertyGroupsByCategoryId(id);
            category.setPropertyGroups(propertyGroups);

            return category;
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 更新类目
     * @param category 类目
     * @throws Exception
     */
    @Override
    public void update(CategoryDTO category) throws Exception {
        category.setGmtModified(dateProvider.getCurrentTime());
        categoryDAO.update(category.clone(CategoryDO.class));

        categoryPropertyRelationshipDAO.removeByCategoryId(category.getId());
        saveCategoryPropertyRelations(category);

        removePropertyGroupRelations(category);
        propertyGroupDAO.removeByCategoryId(category.getId());
        savePropertyGroup(category);
    }

    /**
     * 删除类目的属性分组与属性的关联关系
     * @param category 类目
     * @throws Exception
     */
    private void removePropertyGroupRelations(CategoryDTO category) throws Exception {
        for(PropertyGroupDTO propertyGroup : category.getPropertyGroups()) {
            propertyGroupRelationshipDAO.removeByPropertyGroupId(propertyGroup.getId());
        }
    }

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     * @throws Exception
     */
    private List<PropertyGroupDTO> getPropertyGroupsByCategoryId(Long categoryId) throws Exception {
        List<PropertyGroupDTO> resultPropertyGroups = new ArrayList<>();

        //查询类目关联的属性分组
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(categoryId);

        //查询属性分组与属性的关联关系，以及属性分组关联的属性
        for (PropertyGroupDO propertyGroup : propertyGroups) {
            PropertyGroupDTO resultPropertyGroup = propertyGroup.clone(PropertyGroupDTO.class);

            List<PropertyGroupRelationshipDO> relations = propertyGroupRelationshipDAO.listByPropertyGroupId(propertyGroup.getId());

            resultPropertyGroup.setRelations(ObjectUtils.convertList(relations, PropertyGroupRelationshipDTO.class));

            List<PropertyDTO> properties = new ArrayList<>();
            for (PropertyGroupRelationshipDO relation : relations) {
                properties.add(getPropertyById(relation.getPropertyId()));
            }

            resultPropertyGroup.setProperties(properties);

            resultPropertyGroups.add(resultPropertyGroup);
        }
        return resultPropertyGroups;
    }

    /**
     * 根据id查询属性
     * @param propertyId 属性id
     * @return 属性
     */
    private PropertyDTO getPropertyById(Long propertyId) throws Exception{
        PropertyDO property = propertyDAO.getPropertyById(propertyId);
        return property.clone(PropertyDTO.class);
    }
}
