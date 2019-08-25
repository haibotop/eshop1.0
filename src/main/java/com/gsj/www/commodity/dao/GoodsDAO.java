package com.gsj.www.commodity.dao;

/**
 * 商品管理DAO组件接口
 */
public interface GoodsDAO {
    /**
     * 根据类目id查询商品数量
     * @param categoryId 类目id
     * @return 商品数量
     * @throws Exception
     */
    Long countByCategoryId(Long categoryId) throws Exception;
}
