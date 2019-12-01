package com.gsj.www.wms.domain;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.inventory.updater.AbstractGoodsStockUpdaterFactory;
import com.gsj.www.inventory.updater.GoodsStockUpdater;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.order.domain.OrderInfoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交订单库存更新组件工厂
 * @param <T>
 */
@Component
public class SubmitOrderStockUpdaterFactory<T> extends AbstractGoodsStockUpdaterFactory<T> {
    /**
     * 构造函数
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    public SubmitOrderStockUpdaterFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取要更新库存的商品sku id的集合
     * @param parameter 商品sku id集合
     * @return
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
        List<Long> goodsSkuIds = new ArrayList<Long>();

        List<OrderItemDTO> orderItemDTOS = orderInfoDTO.getOrderItemDTOList();
        for (OrderItemDTO orderItemDTO : orderItemDTOS) {
            goodsSkuIds.add(orderItemDTO.getGoodsSkuId());
        }
        return goodsSkuIds;
    }

    /**
     * 创建商品库存更新组件
     * @param goodsStockDOS 商品库存DO对象集合
     * @param parameter 订单DTO对象
     * @return 商品库存更新组件
     * @throws Exception
     */
    @Override
    protected GoodsStockUpdater create(List<GoodsStockDO> goodsStockDOS, T parameter) throws Exception {
        OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
        Map<Long, OrderItemDTO> orderItemDTOMap = new HashMap<Long, OrderItemDTO>();
        for (OrderItemDTO orderItemDTO : orderInfoDTO.getOrderItemDTOList()) {
            orderItemDTOMap.put(orderItemDTO.getGoodsSkuId(), orderItemDTO);
        }

        return new SubmitOrderStockUpdater(goodsStockDOS,goodsStockDAO,dateProvider,orderItemDTOMap);
    }
}
