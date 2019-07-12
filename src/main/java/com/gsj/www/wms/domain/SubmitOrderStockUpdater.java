package com.gsj.www.wms.domain;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.inventory.updater.AbstractGoodsStockUpdater;
import com.gsj.www.order.domain.OrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 提交库存更新组件
 */
public class SubmitOrderStockUpdater extends AbstractGoodsStockUpdater {
    /**
     * 订单条目DTO对象集合
     */
    private Map<Long, OrderItemDTO> orderItemDTOMap;

    /**
     * 构造函数
     * @param goodsStockDOS 商品库存DO对象
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     * @param orderItemDTOMap 订单条目map集合
     */
    public SubmitOrderStockUpdater(List<GoodsStockDO> goodsStockDOS, GoodsStockDAO goodsStockDAO, DateProvider dateProvider, Map<Long, OrderItemDTO> orderItemDTOMap) {
        super(goodsStockDOS, goodsStockDAO, dateProvider);
        this.orderItemDTOMap = orderItemDTOMap;
    }

    /**
     * 更新销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaledStockQuantity() - orderItemDTO.getPurchaseQuantity());
        }
    }

    /**
     * 更新锁定库存
     * @throws Exception
     */
    @Override
    protected void updateLockedStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setLockedStockQuantity(goodsStockDO.getLockedStockQuantity() + orderItemDTO.getPurchaseQuantity());
        }
    }

    /**
     * 更新已销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaledStockQuantity() throws Exception {

    }
}
