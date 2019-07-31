package com.gsj.www.commodity.service;

import com.gsj.www.commodity.domain.GoodsSkuDTO;

/**
 * 商品中心对外提供的接口
 */
public interface CommodityFacadeService {
    /**
     * 根据id查询商品sku
     * @param goodsSkuId 商品sku id
     * @return 商品sku DTO
     */
    GoodsSkuDTO getGoodsSkuById(Long goodsSkuId);
}
