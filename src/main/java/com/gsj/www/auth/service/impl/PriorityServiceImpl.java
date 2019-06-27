package com.gsj.www.auth.service.impl;

import com.gsj.www.auth.composite.PriorityNode;
import com.gsj.www.auth.dao.AccountPriorityRelationshipDAO;
import com.gsj.www.auth.dao.PriorityDAO;
import com.gsj.www.auth.dao.RolePriorityRelationshipDAO;
import com.gsj.www.auth.domain.PriorityDO;
import com.gsj.www.auth.domain.PriorityDTO;
import com.gsj.www.auth.service.PriorityService;
import com.gsj.www.auth.visitor.PriorityNodeRelateCheckVisitor;
import com.gsj.www.auth.visitor.PriorityNodeRemoveVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块的service组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 7:06
 */
@Service
public class PriorityServiceImpl implements PriorityService {
    private static final Logger logger = LoggerFactory.getLogger(PriorityServiceImpl.class);

    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;
    /**
     * 角色和权限关系管理模块的DAO组件
     */
    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;
    /**
     * 账号和权限关系管理模块的DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    /**
     * 查询根权限
     * @return 根权限集合
     */
    @Override
    public List<PriorityDTO> listRootPriorities() {
        try {
            List<PriorityDO> priorityDOs = priorityDAO.listRootPriorities();
            if(priorityDOs == null) {
                return null;
            }

            List<PriorityDTO> priorityDTOs = new ArrayList<PriorityDTO>(priorityDOs.size());
            for(PriorityDO priorityDO : priorityDOs) {
                priorityDTOs.add(priorityDO.clone(PriorityDTO.class));
            }

            return priorityDTOs;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限id
     * @return 子权限
     */
    @Override
    public List<PriorityDTO> listChildPriorities(Long parentId) {
        try {
            List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(parentId);
            if(priorityDOs == null) {
                return null;
            }

            List<PriorityDTO> priorityDTOs = new ArrayList<PriorityDTO>(priorityDOs.size());
            for(PriorityDO priorityDO : priorityDOs) {
                priorityDTOs.add(priorityDO.clone(PriorityDTO.class));
            }

            return priorityDTOs;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据id查询权限
     * @param id 权限id
     * @return 权限
     */
    @Override
    public PriorityDTO getPriorityById(Long id) {
        try {
            PriorityDO priorityDO = priorityDAO.getPriorityById(id);
            if(priorityDO == null) {
                return null;
            }

            return priorityDO.clone(PriorityDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 新增权限
     * @param priorityDTO 权限DO对象
     */
    @Override
    public Boolean savePriority(PriorityDTO priorityDTO) {
        try {
            priorityDAO.savePriority(priorityDTO.clone(PriorityDO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新权限
     * @param priorityDTO 权限DO对象
     */
    @Override
    public Boolean updatePriority(PriorityDTO priorityDTO) {
        try {
            priorityDAO.updatePriority(priorityDTO.clone(PriorityDO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除权限
     * @param id 权限id
     * @return 处理结果
     */
    @Override
    public Boolean removePriority(Long id) {
        try {
            // 根据id查询权限
            PriorityDO priorityDO = priorityDAO.getPriorityById(id);
            PriorityNode priorityNode = priorityDO.clone(PriorityNode.class);

            // 检查这个权限以及其下任何一个子权限，是否被角色或者账号给关联着
            PriorityNodeRelateCheckVisitor relateCheckVisitor = new PriorityNodeRelateCheckVisitor(
                    priorityDAO, rolePriorityRelationshipDAO, accountPriorityRelationshipDAO);
            relateCheckVisitor.visit(priorityNode);
            Boolean relateCheckResult = relateCheckVisitor.getRelateCheckResult();

            if(relateCheckResult) {
                return false;
            }

            // 递归删除当前权限以及其下所有的子权限
            PriorityNodeRemoveVisitor removeVisitor = new PriorityNodeRemoveVisitor(priorityDAO);
            removeVisitor.visit(priorityNode);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }

        return true;
    }
}
