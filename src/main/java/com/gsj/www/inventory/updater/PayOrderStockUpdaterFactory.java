package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.order.domain.OrderOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayOrderStockUpdaterFactory<T> extends AbstractGoodsStockUpdaterFactory<T> {
    /**
     * 构造函数
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    @Autowired
    public PayOrderStockUpdaterFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取要更新的商品sku id的集合
     * @param parameter 商品sku id集合
     * @return
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        OrderOrderDTO orderOrderDTO = (OrderOrderDTO) parameter;

        List<Long> goodsSkuIds = new ArrayList<Long>();

        List<OrderItemDTO> orderItemDTOS = orderOrderDTO.getOrderItemDTOList();
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
        OrderOrderDTO orderOrderDTO = (OrderOrderDTO) parameter;

        Map<Long, OrderItemDTO> orderItemDTOMap = new HashMap<Long, OrderItemDTO>();
        for (OrderItemDTO orderItemDTO : orderOrderDTO.getOrderItemDTOList()) {
            orderItemDTOMap.put(orderItemDTO.getGoodsId(),orderItemDTO);
        }
        return new PayOrderStockUpdator(goodsStockDOS, goodsStockDAO, dateProvider,orderItemDTOMap);
    }
}
