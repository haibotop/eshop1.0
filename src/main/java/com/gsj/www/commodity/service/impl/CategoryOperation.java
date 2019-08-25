package com.gsj.www.commodity.service.impl;

/**
 * 类目操作
 */
public interface CategoryOperation<T> {
    /**
     * 执行类目操作
     * @param category 类目树节点
     * @return
     * @throws Exception
     */
    T doExecute(Category category) throws Exception;
}
