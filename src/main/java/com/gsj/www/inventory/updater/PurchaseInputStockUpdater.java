package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.wms.domain.PurchaseInputOrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 采购入库库存更新命令
 */
public class PurchaseInputStockUpdater extends AbstractGoodsStockUpdater {
    /**
     * 采购入库单条目DTO集合
     */
    private Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap;

    /**
     * 构造函数
     * @param goodsStockDOS 商品库存DO对象
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    public PurchaseInputStockUpdater(List<GoodsStockDO> goodsStockDOS, GoodsStockDAO goodsStockDAO, DateProvider dateProvider, Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap) {
        super(goodsStockDOS, goodsStockDAO, dateProvider);
        this.purchaseInputOrderItemDTOMap = purchaseInputOrderItemDTOMap;
    }

    /**
     * 更新销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            PurchaseInputOrderItemDTO purchaseInputOrderItemDTO = purchaseInputOrderItemDTOMap.get(goodsStockDO.getId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaledStockQuantity() + purchaseInputOrderItemDTO.getArrivalCount());
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

    }
}
