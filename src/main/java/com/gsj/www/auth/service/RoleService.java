package com.gsj.www.auth.service;

import com.gsj.www.auth.domain.RoleDTO;
import com.gsj.www.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色管理模块service组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 26 6:28
 */
public interface RoleService {
    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    List<RoleDTO> listByPage(RoleQuery query);

    /**
     * 根据id查询角色DO对象
     * @param id 角色id
     * @return 角色DO对象
     */
    RoleDTO getById(Long id);

    /**
     * 新增角色
     * @param role 角色DO对象
     * @return
     */
    Boolean save(RoleDTO role);

    /**
     * 更新角色
     * @param role 角色DO对象
     * @return
     */
    Boolean update(RoleDTO role);

    /**
     * 删除角色
     * @param id 角色id
     * @return
     */
    Boolean remove(Long id);
}
