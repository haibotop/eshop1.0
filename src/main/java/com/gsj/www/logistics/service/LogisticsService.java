package com.gsj.www.logistics.service;

import com.gsj.www.commodity.domain.GoodsSkuDTO;

/**
 * 物流中心对外提供的接口
 * @author holy
 */
public interface LogisticsService {

    /**
     * 计算商品sku的运费
     * @param goodsSkuDTO 商品sku DTO
     * @return 商品sku的运费
     */
    Double calculateFreight(GoodsSkuDTO goodsSkuDTO);

}

