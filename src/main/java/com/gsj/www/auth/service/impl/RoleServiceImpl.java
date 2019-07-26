package com.gsj.www.auth.service.impl;

import com.gsj.www.auth.dao.AccountRoleRelationshipDAO;
import com.gsj.www.auth.dao.RoleDAO;
import com.gsj.www.auth.dao.RolePriorityRelationshipDAO;
import com.gsj.www.auth.domain.*;
import com.gsj.www.auth.service.RoleService;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理模块service组件
 *
 * @author Holy
 * @create 2019 - 07 - 26 6:35
 */
@Service
public class RoleServiceImpl implements RoleService{
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    /**
     * 角色管理模块DAO组件
     */
    @Autowired
    private RoleDAO roleDAO;
    /**
     * 角色权限关系管理模块DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;
    /**
     * 账号角色关系管理模块DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    @Override
    public List<RoleDTO> listByPage(RoleQuery query) {
        try{
            List<RoleDO> roles = roleDAO.listByPage(query);
            return ObjectUtils.convertList(roles,RoleDTO.class);
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 根据id查询角色DO对象
     * @param id 角色id
     * @return 角色DO对象
     */
    @Override
    public RoleDTO getById(Long id) {
        try {
            RoleDO role = roleDAO.getById(id);
            if(role == null){
                return null;
            }

            RoleDTO resultRole = role.clone(RoleDTO.class);

            List<RolePriorityRelationshipDO> relations = rolePriorityRelationshipDAO.listByRoleId(id);
            resultRole.setRolePriorityRelations(ObjectUtils.convertList(relations, RolePriorityRelationshipDTO.class));

            return resultRole;
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 新增角色
     * @param role 角色DO对象
     * @return
     */
    @Override
    public Boolean save(RoleDTO role) {
        try {
            roleDAO.save(role.clone(RoleDO.class));
            for (RolePriorityRelationshipDTO rolePriorityRelation : role.getRolePriorityRelations()) {
                rolePriorityRelationshipDAO.save(rolePriorityRelation.clone(RolePriorityRelationshipDO.class));
            }
            return true;
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 更新角色
     * @param role 角色DO对象
     * @return
     */
    @Override
    public Boolean update(RoleDTO role) {
        try {
            roleDAO.update(role.clone(RoleDO.class));
            rolePriorityRelationshipDAO.removeByRoleId(role.getId());

            for (RolePriorityRelationshipDTO rolePriorityRelation : role.getRolePriorityRelations()) {
                rolePriorityRelationshipDAO.save(rolePriorityRelation.clone(RolePriorityRelationshipDO.class));
            }
            return true;
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 删除角色
     * @param id 角色id
     * @return
     */
    @Override
    public Boolean remove(Long id) {
        try{
            Long count = accountRoleRelationshipDAO.countByRoleId(id);
            if(count > 0L){
                return false;
            }

            roleDAO.remove(id);
            rolePriorityRelationshipDAO.removeByRoleId(id);

            return true;
        }catch (Exception e) {
            logger.error("error",e);
            return false;
        }
    }
}
