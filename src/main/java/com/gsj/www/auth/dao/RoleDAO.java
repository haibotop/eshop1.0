package com.gsj.www.auth.dao;

import com.gsj.www.auth.domain.RoleDO;
import com.gsj.www.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色管理模块DAO组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 25 22:41
 */
public interface RoleDAO {
    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    List<RoleDO> listByPage(RoleQuery query);

    /**
     * 根据id查询角色DO对象
     * @param id 角色id
     * @return 角色DO对象
     */
    RoleDO getById(Long id);

    /**
     * 新增角色
     * @param role
     * @return
     */
    Boolean save(RoleDO role);

    /**
     * 更新角色
     * @param role 角色DO对象
     * @return
     */
    Boolean update(RoleDO role);

    /**
     * 删除角色
     * @param id 角色id
     * @return
     */
    Boolean remove(Long id);
}
