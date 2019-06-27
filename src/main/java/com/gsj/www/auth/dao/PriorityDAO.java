package com.gsj.www.auth.dao;

import com.gsj.www.auth.domain.PriorityDO;

import java.util.List;

/**
 * 权限管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:54
 */
public interface PriorityDAO {
    /**
     * 查询根权限
     * @return 根权限集合
     */
    List<PriorityDO> listRootPriorities();

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限id
     * @return 子权限
     */
    List<PriorityDO> listChildPriorities(Long parentId);

    /**
     * 根据id查询权限
     * @param id 权限id
     * @return 权限
     */
    PriorityDO getPriorityById(Long id);

    /**
     * 新增权限
     * @param priorityDO 权限DO对象
     */
    Long savePriority(PriorityDO priorityDO);

    /**
     * 更新权限
     * @param priorityDO 权限DO对象
     */
    Boolean updatePriority(PriorityDO priorityDO);

    /**
     * 删除权限
     * @param id 权限id
     */
    Boolean removePriority(Long id);
}
