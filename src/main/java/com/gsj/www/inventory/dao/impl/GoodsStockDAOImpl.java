package com.gsj.www.inventory.dao.impl;

import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.inventory.mapper.GoodsStockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品库存管理模块的DAO组件
 */
@Repository
public class GoodsStockDAOImpl implements GoodsStockDAO {
    private static final Logger logger = LoggerFactory.getLogger(GoodsStockDAOImpl.class);
    /**
     * 商品库存管理模块的mapper组件
     */
    @Autowired
    private GoodsStockMapper goodsStockMapper;

    /**
     * 根据商品sku id查询商品库存
     * @param goodsSkuId 商品sku id
     * @return 商品库存
     */
    @Override
    public GoodsStockDO getGoodsStockBySkuId(Long goodsSkuId) {
        try {
            return goodsStockMapper.getGoodsStockBySkuId(goodsSkuId);
        }catch (Exception e){
            logger.error("error",e);
        }
        return null;
    }

    /**
     * 新增商品库存
     * @param goodsStockDO 商品库存DO对象
     * @return
     */
    @Override
    public Boolean saveGoodsStock(GoodsStockDO goodsStockDO) {
        try {
            goodsStockMapper.saveGoodsStock(goodsStockDO);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 更新商品库存
     * @param goodsStockDO 商品库存DO对象
     * @return
     */
    @Override
    public Boolean updateGoodsStock(GoodsStockDO goodsStockDO) {
        try {
            goodsStockMapper.updateGoodsStock(goodsStockDO);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }
}
