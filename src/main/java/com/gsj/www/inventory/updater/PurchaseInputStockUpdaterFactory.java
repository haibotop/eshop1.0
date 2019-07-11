package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderItemDTO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购入库库存更新命令的工厂
 * @param <T>
 */
@Component
public class PurchaseInputStockUpdaterFactory<T> extends AbstractGoodsStockUpdaterFactory<T> {
    /**
     * 构造函数
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider  日期辅助组件
     */
    public PurchaseInputStockUpdaterFactory(GoodsStockDAO goodsStockDAO, DateProvider dateProvider) {
        super(goodsStockDAO, dateProvider);
    }

    /**
     * 获取商品sku id集合
     * @param parameter 商品sku id集合
     * @return 商品sku id集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) parameter;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOS = purchaseInputOrderDTO.getPurchaseInputOrderItemDTOS();

        if(purchaseInputOrderItemDTOS == null && purchaseInputOrderItemDTOS.size() == 0){
            return new ArrayList<Long>();
        }

        List<Long> goodsSkuIds = new ArrayList<Long>(purchaseInputOrderItemDTOS.size());

        for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOS) {
            goodsSkuIds.add(purchaseInputOrderItemDTO.getGoodsSkuId());
        }
        return goodsSkuIds;
    }

    /**
     * 创建库存更新命令
     * @param goodsStockDOS 商品库存DO对象集合
     * @param parameter
     * @return 库存更新命令
     * @throws Exception
     */
    @Override
    protected GoodsStockUpdater create(List<GoodsStockDO> goodsStockDOS, T parameter) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) parameter;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOS = purchaseInputOrderDTO.getPurchaseInputOrderItemDTOS();

        Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap = new HashMap<Long, PurchaseInputOrderItemDTO>();

        if(purchaseInputOrderItemDTOS != null && purchaseInputOrderItemDTOS.size() > 0){
            for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOS) {
                purchaseInputOrderItemDTOMap.put(purchaseInputOrderItemDTO.getGoodsSkuId(), purchaseInputOrderItemDTO);
            }
        }
        return new PurchaseInputStockUpdater(goodsStockDOS, goodsStockDAO, dateProvider, purchaseInputOrderItemDTOMap);
    }
}
