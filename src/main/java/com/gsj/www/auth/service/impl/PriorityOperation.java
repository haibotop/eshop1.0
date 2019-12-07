package com.gsj.www.auth.service.impl;

/**
 * 权限操作接口
 * @author holy
 */
public interface PriorityOperation<T> {
    /**
     * 执行这个操作
     * @param priority
     * @return
     * @throws Exception
     */
    T doExecute(Priority priority) throws Exception;
}
