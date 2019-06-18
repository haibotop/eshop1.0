package com.gsj.www.promotion.service;

import com.gsj.www.promotion.domain.PromotionActivityDTO;

import java.util.List;

/**
 * 促销中心对外提供的接口
 */
public interface PromotionFacadeService {

    /**
     * 根据商品id查询促销活动
     * @param goodsId 商品id
     * @return 促销活动
     */
    List<PromotionActivityDTO> listPromotionActivitiesByGoodsId(Long goodsId);

}
