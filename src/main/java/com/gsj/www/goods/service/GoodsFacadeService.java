package com.gsj.www.goods.service;

import com.gsj.www.goods.domain.GoodsSkuDTO;

/**
 * 商品中心对外提供的接口
 */
public interface GoodsFacadeService {

    /**
     * 根据id查询商品sku
     * @param goodsSkuId 商品sku id
     * @return 商品sku DTO
     */
    GoodsSkuDTO getGoodsSkuById(Long goodsSkuId);

}

