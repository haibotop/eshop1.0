package com.gsj.www.commodity.controller;

import com.gsj.www.commodity.domain.*;
import com.gsj.www.commodity.service.CategoryService;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 类目管理controller类
 */
@RestController
@RequestMapping("/commodity/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    /**
     * 类目管理service组件
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询根类目
     * @return 根类目集合
     */
    @GetMapping("/root")
    public List<CategoryVO> listRoots(){
        try {
            List<CategoryDTO> categories = categoryService.listRoots();
            List<CategoryVO> resultCategories = ObjectUtils.convertList(categories,CategoryVO.class);
            return resultCategories;
        }catch (Exception e){
            logger.error("error",e);
            return new ArrayList<CategoryVO>();
        }
    }

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     */
    @GetMapping("/children/{id}")
    public List<CategoryVO> listChildren(@PathVariable("id") Long id){
        try {
            List<CategoryDTO> categories = categoryService.listChildren(id);
            List<CategoryVO> resultCategories = ObjectUtils.convertList(categories,CategoryVO.class);
            return resultCategories;
        }catch (Exception e){
            logger.error("error",e);
            return new ArrayList<CategoryVO>();
        }
    }

    /**
     * 新增类目
     * @param category 类目
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody CategoryVO category){
        try {
            //转换类目基本信息
            CategoryDTO targetCategory = category.clone(CategoryDTO.class);

            //转换类目与属性的关联关系
            List<CategoryPropertyRelationshipDTO> targetPropertyRelations = ObjectUtils.convertList(category.getPropertyRelations(), CategoryPropertyRelationshipDTO.class);
            targetCategory.setPropertyRelations(targetPropertyRelations);

            //转换属性分组
            List<PropertyGroupDTO> targetPropertyGroups = new ArrayList<PropertyGroupDTO>();
            targetCategory.setPropertyGroups(targetPropertyGroups);

            for (PropertyGroupVO group : category.getPropertyGroups()) {
                PropertyGroupDTO targetGroup = group.clone(PropertyGroupDTO.class);
                targetGroup.setRelations(ObjectUtils.convertList(group.getRelations(), PropertyGroupRelationshipDTO.class));
                targetPropertyGroups.add(targetGroup);
            }

            //执行类目新增的操作
            categoryService.save(targetCategory);
            return true;
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }
}
