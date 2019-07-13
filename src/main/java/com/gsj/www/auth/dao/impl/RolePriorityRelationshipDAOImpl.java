package com.gsj.www.auth.dao.impl;

import com.gsj.www.auth.dao.RolePriorityRelationshipDAO;
import com.gsj.www.auth.domain.RolePriorityRelationshipDO;
import com.gsj.www.auth.mapper.RolePriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 角色和权限关系管理模块的DAO组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 7:02
 */
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO{
    private static final Logger logger = LoggerFactory.getLogger(RolePriorityRelationshipDAOImpl.class);
    /**
     * 角色和权限关系管理模块的mapper组件
     */
    @Autowired
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Override
    public Long getCountByPriorityId(Long priorityId) {
        return rolePriorityRelationshipMapper.getCountByPriorityId(priorityId);
    }

    @Override
    public Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO) {
        try{
            rolePriorityRelationshipMapper.save(rolePriorityRelationshipDO);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }
}
