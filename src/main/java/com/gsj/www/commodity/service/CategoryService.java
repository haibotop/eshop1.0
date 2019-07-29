package com.gsj.www.commodity.service;

import com.gsj.www.commodity.domain.CategoryDTO;

import java.util.List;

/**
 * 类目管理service组件接口
 */
public interface CategoryService {
    /**
     * 查询根类目
     * @return 根类目集合
     */
    List<CategoryDTO> listRoots();

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     */
    List<CategoryDTO> listChildren(Long id);

    /**
     * 新增类目
     * @param category 类目
     * @return 处理结果
     */
    Boolean save(CategoryDTO category);
}
