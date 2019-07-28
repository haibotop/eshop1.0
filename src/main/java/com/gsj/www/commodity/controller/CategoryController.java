package com.gsj.www.commodity.controller;

import com.gsj.www.commodity.domain.CategoryDTO;
import com.gsj.www.commodity.domain.CategoryVO;
import com.gsj.www.commodity.service.CategoryService;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
