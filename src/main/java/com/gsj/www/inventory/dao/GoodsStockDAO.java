package com.gsj.www.inventory.dao;

import com.gsj.www.inventory.domain.GoodsStockDO;

/**
 * 商品库存管理模块的DAO组件接口
 */
public interface GoodsStockDAO {
    /**
     * 根据商品sku id查询商品库存
     * @param goodsSkuId 商品sku id
     * @return 商品库存
     */
    GoodsStockDO getGoodsStockBySkuId(Long goodsSkuId);

    /**
     * 新增商品库存
     * @param goodsStockDO 商品库存DO对象
     * @return 处理结果
     */
    Boolean saveGoodsStock(GoodsStockDO goodsStockDO);

    /**
     * 更新商品库存
     * @param goodsStockDO 商品库存DO对象
     * @return
     */
    Boolean updateGoodsStock(GoodsStockDO goodsStockDO);
}
