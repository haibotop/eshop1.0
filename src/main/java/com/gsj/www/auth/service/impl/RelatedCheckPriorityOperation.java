package com.gsj.www.auth.service.impl;

import com.gsj.www.auth.dao.AccountPriorityRelationshipDAO;
import com.gsj.www.auth.dao.PriorityDAO;
import com.gsj.www.auth.dao.RolePriorityRelationshipDAO;
import com.gsj.www.auth.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查权限是否被关联的操作
 * @author holy
 */
@Component
@Scope("prototype")
public class RelatedCheckPriorityOperation implements PriorityOperation<Boolean> {
    /**
     * 关联检查结果
     */
    private Boolean relateCheckResult = false;
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
     * 访问跟权限节点
     * @param priority
     * @return
     * @throws Exception
     */
    @Override
    public Boolean doExecute(Priority priority) throws Exception {
        List<PriorityDO> priorityDOS = priorityDAO.listChildPriorities(priority.getId());

        if(priorityDOS != null && priorityDOS.size() > 0){
            for (PriorityDO priorityDO : priorityDOS) {
                Priority priority1 = priorityDO.clone(Priority.class);
                priority1.execute(this);
            }
        }

        if(relateCheck(priority)){
            this.relateCheckResult = true;
        }

        return this.relateCheckResult;
    }

    /**
     * 检查权限是否被任何一个角色或者是账号关联了
     * @param priority 权限树节点
     * @return 是否被任何一个角色或者账号关联了，如果有关联则为true，如果没有关联则为false
     */
    private boolean relateCheck(Priority priority) {
        Long roleRelatedCount = rolePriorityRelationshipDAO.countByPriorityId(priority.getId());
        if(roleRelatedCount != null && roleRelatedCount > 0){
            return true;
        }

        Long accountRelatedCount = accountPriorityRelationshipDAO.getCountByPriorityId(priority.getId());
        if(accountRelatedCount != null && accountRelatedCount > 0){
            return true;
        }

        return false;
    }

    public Boolean getRelateCheckResult(){
        return relateCheckResult;
    }
}
