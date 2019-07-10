package com.gsj.www.promotion.service.impl;

import com.gsj.www.promotion.constant.PromotionActivityStatus;
import com.gsj.www.promotion.domain.PromotionActivityDTO;
import com.gsj.www.promotion.service.PromotionFacadeService;
import com.sun.source.util.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 促销中心对外接口service组件
 */
@Service
public class PromotionFacadeServiceImpl implements PromotionFacadeService {
    private static final Logger logger = LoggerFactory.getLogger(PromotionFacadeServiceImpl.class);

    /**
     * 根据商品id查询促销活动
     * @param goodsId 商品id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDTO> listPromotionActivitiesByGoodsId(Long goodsId) {
        List<PromotionActivityDTO> promotionActivityDTOS = new ArrayList<PromotionActivityDTO>();

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            PromotionActivityDTO promotionActivityDTO = new PromotionActivityDTO();
            promotionActivityDTO.setId(1L);
            promotionActivityDTO.setPromotionActivityComment("测试促销活动1");
            promotionActivityDTO.setGmtCreate(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO.setGmtModified(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO.setPromotionActivityEndTime(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO.setPromotionActivityName("测试促销活动1");
            promotionActivityDTO.setPromotionActivityRule("测试促销活动的规则");
            promotionActivityDTO.setPromotionActivityStartTime(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO.setPromotionActivityStatus(PromotionActivityStatus.ENABLED);
            promotionActivityDTOS.add(promotionActivityDTO);

            PromotionActivityDTO promotionActivityDTO1= new PromotionActivityDTO();
            promotionActivityDTO1.setGmtCreate(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO1.setGmtModified(dateFormat.parse("2019-07-09 09:30:000"));
            promotionActivityDTO1.setId(1L);
            promotionActivityDTO1.setPromotionActivityComment("测试促销活动2");
            promotionActivityDTO1.setPromotionActivityEndTime(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO1.setPromotionActivityName("测试促销活动2");
            promotionActivityDTO1.setPromotionActivityRule("测试促销活动的规则");
            promotionActivityDTO1.setPromotionActivityStartTime(dateFormat.parse("2019-07-09 09:30:00"));
            promotionActivityDTO1.setPromotionActivityStatus(PromotionActivityStatus.ENABLED);
            promotionActivityDTOS.add(promotionActivityDTO1);
        }catch (Exception e){
            logger.error("error",e);
            return new ArrayList<PromotionActivityDTO>();
        }
        return promotionActivityDTOS;
    }
}
