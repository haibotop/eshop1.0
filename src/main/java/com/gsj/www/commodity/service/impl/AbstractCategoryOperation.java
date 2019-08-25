package com.gsj.www.commodity.service.impl;

import com.gsj.www.commodity.dao.CategoryDAO;
import com.gsj.www.commodity.domain.CategoryDO;

import java.util.List;

/**
 * 类目操作的抽象基类
 */
public abstract class AbstractCategoryOperation<T> implements CategoryOperation<T> {
    /**
     * 类目管理DAO组件
     */
    protected CategoryDAO categoryDAO;

    /**
     * 构造函数
     * @param categoryDAO 类目管理DAO组件
     */
    public AbstractCategoryOperation(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    /**
     * 执行类目操作
     * @param category 类目树节点
     * @return
     * @throws Exception
     */
    @Override
    public T doExecute(Category category) throws Exception {
        doExecuteForChildren(category);
        doRealExecute(category);
        return getResult();
    }

    /**
     * 获取操作的执行结果
     * @return 操作的执行结果
     */
    protected abstract T getResult() throws Exception;

    /**
     * 执行实际的操作
     * @param category 类目
     */
    protected abstract void doRealExecute(Category category) throws Exception;

    /**
     * 递归对子类目执行当前操作
     * @param category 当前类目
     */
    private void doExecuteForChildren(Category category) throws Exception{
        List<CategoryDO> children = categoryDAO.listChildren(category.getCategoryId());
        if(children != null && children.size() > 0){
            for (CategoryDO child : children) {
                Category chileCategory = new Category(child.getId());
                chileCategory.execute(this);
            }
        }
    }

}
