package com.gsj.www.auth.service.impl;

import com.gsj.www.auth.dao.PriorityDAO;
import com.gsj.www.auth.domain.PriorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 删除权限操作
 * @author holy
 */
@Component
@Scope("prototype")
public class RemovePriorityOperation implements PriorityOperation<Boolean>{
    /**
     * 权限管理模块的DAO组件
     */
    @Autowired
    private PriorityDAO priorityDAO;

    /**
     * 访问权限树节点
     * @param priority 权限树节点
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

        removePriority(priority);

        return true;
    }

    /**
     * 删除权限
     * @param priority 权限树节点
     */
    private void removePriority(Priority priority){
        priorityDAO.removePriority(priority.getId());
    }
}
