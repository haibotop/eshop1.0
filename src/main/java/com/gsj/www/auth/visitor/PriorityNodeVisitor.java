package com.gsj.www.auth.visitor;

import com.gsj.www.auth.composite.PriorityNode;

/**
 * 权限树节点的访问者接口
 *
 * @author Holy
 * @create 2019 - 06 - 21 6:42
 */
public interface PriorityNodeVisitor {
    /**
     * 访问权限树节点
     * @param node 权限树节点
     */
    void visit(PriorityNode node);
}
