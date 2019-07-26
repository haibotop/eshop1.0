package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.RoleDAO;
import com.gsj.www.auth.domain.RoleDO;
import com.gsj.www.auth.domain.RoleQuery;
import com.gsj.www.auth.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色管理模块DAO组件
 *
 * @author Holy
 * @create 2019 - 07 - 25 22:51
 */
@Repository
public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    /**
     * 角色管理模块mapper组件
     */
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    @Override
    public List<RoleDO> listByPage(RoleQuery query) {
        try {
            return roleMapper.listByPage(query);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查询角色DO对象
     * @param id 角色 id
     * @return 角色DO对象
     */
    @Override
    public RoleDO getById(Long id) {
        try {
            return roleMapper.getById(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 新增角色
     * @param role 角色DO对象
     */
    @Override
    public Boolean save(RoleDO role) {
        try {
            roleMapper.save(role);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新角色
     * @param role 角色DO对象
     */
    @Override
    public Boolean update(RoleDO role) {
        try {
            roleMapper.update(role);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除角色
     * @param id 角色id
     */
    @Override
    public Boolean remove(Long id) {
        try {
            roleMapper.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
