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

    /**
     * 新增类目
     * @param category 类目
     * @return 主键id
     */
    Long save(CategoryDO category);

    /**
     * 根据id查询类目
     * @param id 类目id
     * @return 类目
     */
    CategoryDO getById(Long id);

    /**
     * 更新类目
     * @param category 类目
     */
    void update(CategoryDO category) throws Exception;

    /**
     * 删除类目
     * @param id 类目id
     * @throws Exception
     */
    void remove(Long id) throws Exception;
}
