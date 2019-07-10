package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.constant.StockStatus;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存更新命令工厂的父亲
 * @param <T>
 */
public abstract class AbstractGoodsStockUpdaterFactory<T> implements GoodsStockUpdaterFactory<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractGoodsStockUpdaterFactory.class);
    /**
     * 商品库存管理模块的DAO组件
     */
    protected GoodsStockDAO goodsStockDAO;
    /**
     * 日期辅助组件
     */
    protected DateProvider dateProvider;

    /**
     * 构造函数
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider 日期辅助组件
     */
    public AbstractGoodsStockUpdaterFactory(GoodsStockDAO goodsStockDAO,DateProvider dateProvider){
        this.dateProvider = dateProvider;
        this.goodsStockDAO = goodsStockDAO;
    }

    /**
     * 创建库存更新命令
     * @param parameter 参数对象
     * @return
     */
    @Override
    public GoodsStockUpdater create(T parameter) {
        try {
            List<Long> goodsSkuIds = getGoodsSkuIds(parameter);
            List<GoodsStockDO> goodsStockDOS = createGoodsStockDOS(goodsSkuIds);
            return create(goodsStockDOS,parameter);
        }catch (Exception e){
            logger.error("error",e);
        }
        return null;
    }

    /**
     * 获取商品sku id集合
     * @param parameter 商品sku id集合
     * @return
     * @throws Exception
     */
    protected abstract List<Long> getGoodsSkuIds(T parameter) throws Exception;

    /**
     * 穿件库存更新命令
     * @param goodsStockDOS 商品库存DO对象集合
     * @param parameter
     * @return 库存更新命令
     * @throws Exception
     */
    protected abstract GoodsStockUpdater create(List<GoodsStockDO> goodsStockDOS, T parameter) throws Exception;

    /**
     * 创建商品库存DO对象集合
     * @param goodsSkuIds 商品sku id集合
     * @return 商品库存DO对象集合
     * @throws Exception
     */
    private List<GoodsStockDO> createGoodsStockDOS(List<Long> goodsSkuIds) throws Exception{
        List<GoodsStockDO> goodsStockDOS = new ArrayList<GoodsStockDO>(goodsSkuIds.size());

        for (Long goodsSkuId : goodsSkuIds) {
            GoodsStockDO goodsStockDO = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);

            if(goodsStockDO == null){
                goodsStockDO = new GoodsStockDO();
                goodsStockDO.setGoodsSkuId(goodsSkuId);
                goodsStockDO.setSaleStockQuantity(0L);
                goodsStockDO.setLockedStockQuantity(0L);
                goodsStockDO.setSaledStockQuantity(0L);
                goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
                goodsStockDO.setGmtCreate(dateProvider.getCurrentTime());
                goodsStockDO.setGmtModified(dateProvider.getCurrentTime());

                goodsStockDAO.saveGoodsStock(goodsStockDO);
            }

            goodsStockDOS.add(goodsStockDO);
        }
        return goodsStockDOS;
    }
}
