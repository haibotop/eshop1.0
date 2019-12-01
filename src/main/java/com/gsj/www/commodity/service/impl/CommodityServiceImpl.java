package com.gsj.www.commodity.service.impl;

import com.gsj.www.commodity.domain.GoodsSkuDTO;
import com.gsj.www.commodity.service.CommodityService;
import com.gsj.www.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品中心对外接口service实现类组件
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    /**
     * 时间辅助类
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 根据id查询商品sku
     * @param goodsSkuId 商品sku id
     * @return 商品sku DTO
     */
    @Override
    public GoodsSkuDTO getGoodsSkuById(Long goodsSkuId) {
        try {
            GoodsSkuDTO goodsSkuDTO = new GoodsSkuDTO();
            goodsSkuDTO.setGoodsSkuCode("FDL04300234");
            goodsSkuDTO.setSaleProperties("机身颜色:白色,机身存储:256G");
            goodsSkuDTO.setGoodsId(999L);
            goodsSkuDTO.setGoodsName("iPhonePlus");
            goodsSkuDTO.setGoodsLength(125.90);
            goodsSkuDTO.setGoodsWidth(29.60);
            goodsSkuDTO.setGoodsHeight(59.50);
            goodsSkuDTO.setGrossWeight(599.80);
            goodsSkuDTO.setDiscountPrice(8999.00);
            goodsSkuDTO.setPurchasePrice(6856.70);
            goodsSkuDTO.setSalePrice(9599.50);
            goodsSkuDTO.setGmtCreate(dateProvider.getCurrentTime());
            goodsSkuDTO.setGmtModified(dateProvider.getCurrentTime());

            return goodsSkuDTO;
        }catch (Exception e){
            logger.error("error",e);
        }
        return null;
    }
}
