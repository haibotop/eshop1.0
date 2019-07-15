package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 提交订单库存更新组件工厂
 *
 * @author Holy
 * @create 2019 - 07 - 15 22:55
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
     * 获取要更新库存的商品sku
     * @param parameter 商品sku id集合
     * @return
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        return null;
    }

    @Override
    protected GoodsStockUpdater create(List<GoodsStockDO> goodsStockDOS, T parameter) throws Exception {
        return null;
    }
}
