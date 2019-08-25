package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.GoodsDAO;
import com.gsj.www.commodity.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品管理DAO组件
 */
@Repository
public class GoodsDAOImpl implements GoodsDAO {
    /**
     * 商品管理mapper组件
     */
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 根据类目id查询商品数量
     * @param categoryId 类目id
     * @return 商品数量
     * @throws Exception
     */
    @Override
    public Long countByCategoryId(Long categoryId) throws Exception {
        return goodsMapper.countByCategoryId(categoryId);
    }
}
