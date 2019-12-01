package com.gsj.www.logistics.service.impl;

import com.gsj.www.commodity.domain.GoodsSkuDTO;
import com.gsj.www.logistics.service.LogisticsService;

/**
 * 物流中心接口
 * @author holy
 */
public class LogisticsServiceImpl implements LogisticsService {
    /**
     * 计算商品sku的运费
     * @param goodsSkuDTO 商品sku DTO
     * @return 商品sku的运费
     */
    @Override
    public Double calculateFreight(GoodsSkuDTO goodsSkuDTO) {
        return 5.5;
    }
}
