package com.gsj.www.auth.controller;

import com.gsj.www.auth.domain.*;
import com.gsj.www.auth.service.RoleService;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理模块controller组件
 * @author holy
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    /**
     * 角色管理模块service组件
     */
    @Autowired
    private RoleService roleService;

    /**
     * 分页查询角色
     * @param roleQuery 查询条件
     * @return 角色VO集合
     */
    @GetMapping("/")
    public List<RoleVO> listByPage(RoleQuery roleQuery){
        try {
            List<RoleDTO> roles = roleService.listByPage(roleQuery);
            List<RoleVO> resultRoles = ObjectUtils.convertList(roles,RoleVO.class);
            return resultRoles;
        }catch (Exception e){
            logger.error("error",e);
            return new ArrayList<RoleVO>();
        }
    }

    /**
     * 根据id查询角色
     * @param id 角色id
     * @return 角色
     */
    @GetMapping("/{id}")
    public RoleVO getById(@PathVariable("id") Long id){
        try {
            RoleDTO role = roleService.getById(id);
            RoleVO resultRole = role.clone(RoleVO.class);

            List<RolePriorityRelationshipDTO> relationshipDTOS = role.getRolePriorityRelations();
            List<RolePriorityRelationshipVO> resultRelations = ObjectUtils.convertList(relationshipDTOS,RolePriorityRelationshipVO.class);

            resultRole.setRolePriorityRelations(resultRelations);
            return resultRole;
        }catch (Exception e){
            logger.error("error",e);
            return new RoleVO();
        }
    }

    /**
     * 新增角色
     * @param role 角色
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody RoleVO role){
        try {
            RoleDTO targetRole = role.clone(RoleDTO.class);
            List<RolePriorityRelationshipDTO> targetRelations = ObjectUtils.convertList(role.getRolePriorityRelations(),RolePriorityRelationshipDTO.class);
            targetRole.setRolePriorityRelations(targetRelations);
            return roleService.save(targetRole);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 更新角色
     * @param role 角色
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody RoleVO role){
        try {
            RoleDTO targetRole = role.clone(RoleDTO.class);
            List<RolePriorityRelationshipDTO> targetRelations = ObjectUtils.convertList(role.getRolePriorityRelations(),RolePriorityRelationshipDTO.class);

            targetRole.setRolePriorityRelations(targetRelations);

            return roleService.update(targetRole);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 删除角色
     * @param id 角色id
     * @return 处理结果
     */
    @GetMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        try {
            return roleService.remove(id);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }
}
