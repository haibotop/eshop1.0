package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.wms.domain.ReturnGoodsInputOrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 退货入库
 */
public class ReturnGoodsInputStockUpdater extends AbstractGoodsStockUpdater {
    /**
     * 退货入库单条目DTO集合
     */
    private Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap;

    /**
     * 构造函数
     * @param goodsStockDOS 商品库存DO对象
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     * @param returnGoodsInputOrderItemDTOMap 退货入库单条目DTO集合
     */
    public ReturnGoodsInputStockUpdater(List<GoodsStockDO> goodsStockDOS, GoodsStockDAO goodsStockDAO, DateProvider dateProvider, Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap) {
        super(goodsStockDOS, goodsStockDAO, dateProvider);
        this.returnGoodsInputOrderItemDTOMap = returnGoodsInputOrderItemDTOMap;
    }

    /**
     * 跟新销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaledStockQuantity() + returnGoodsInputOrderItemDTO.getArrivalCount());
        }
    }

    /**
     * 更新锁定库存
     * @throws Exception
     */
    @Override
    protected void updateLockedStockQuantity() throws Exception {

    }

    /**
     * 更新已销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaledStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaledStockQuantity() - returnGoodsInputOrderItemDTO.getArrivalCount());
        }
    }
}
