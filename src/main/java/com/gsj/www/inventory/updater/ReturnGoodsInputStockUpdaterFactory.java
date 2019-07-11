package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderDTO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退货入库库存更新命令的工厂
 */
@Component
public class ReturnGoodsInputStockUpdaterFactory<T> extends AbstractGoodsStockUpdaterFactory<T> {
    /**
     * 构造函数
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    public ReturnGoodsInputStockUpdaterFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取商品sku id集合
     * @return 商品sku id集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) parameter;
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs =
                returnGoodsInputOrderDTO.getReturnGoodsInputOrderItemDTOS();

        if(returnGoodsInputOrderItemDTOs == null || returnGoodsInputOrderItemDTOs.size() == 0) {
            return new ArrayList<Long>();
        }

        List<Long> goodsSkuIds = new ArrayList<Long>(returnGoodsInputOrderItemDTOs.size());

        for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO : returnGoodsInputOrderItemDTOs) {
            goodsSkuIds.add(returnGoodsInputOrderItemDTO.getGoodsSkuId());
        }

        return goodsSkuIds;
    }

    /**
     * 创建库存更新命令
     * @param goodsStockDOs 商品库存DO对象集合
     * @return 库存更新命令
     * @throws Exception
     */
    @Override
    protected GoodsStockUpdater create(List<GoodsStockDO> goodsStockDOs, T parameter) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) parameter;
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs =
                returnGoodsInputOrderDTO.getReturnGoodsInputOrderItemDTOS();

        Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap =
                new HashMap<Long, ReturnGoodsInputOrderItemDTO>();

        if(returnGoodsInputOrderItemDTOs != null && returnGoodsInputOrderItemDTOs.size() > 0) {
            for(ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO : returnGoodsInputOrderItemDTOs) {
                returnGoodsInputOrderItemDTOMap.put(returnGoodsInputOrderItemDTO.getGoodsSkuId(),
                        returnGoodsInputOrderItemDTO);
            }
        }

        return new ReturnGoodsInputStockUpdater(goodsStockDOs, goodsStockDAO,
                dateProvider, returnGoodsInputOrderItemDTOMap);
    }
}
