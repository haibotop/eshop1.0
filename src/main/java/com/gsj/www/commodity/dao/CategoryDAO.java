package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.CategoryDO;

import java.util.List;

/**
 * 类目管理DAO组件接口
 */
public interface CategoryDAO {
    /**
     * 查询根类目
     * @return 根类目集合
     */
    List<CategoryDO> listRoots();

    /**
     * 查询子类目
     * @param id 父类目id
     * @return 子类目集合
     */
    List<CategoryDO> listChildren(Long id);
}
