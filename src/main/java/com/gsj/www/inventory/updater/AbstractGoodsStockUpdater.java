package com.gsj.www.inventory.updater;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.constant.StockStatus;
import com.gsj.www.inventory.dao.GoodsStockDAO;
import com.gsj.www.inventory.domain.GoodsStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 商品库存更新命令的抽象基类
 */
public abstract class AbstractGoodsStockUpdater implements GoodsStockUpdater {
    private static final Logger logger = LoggerFactory.getLogger(AbstractGoodsStockUpdater.class);
    /**
     * 商品库存DO对象
     */
    protected List<GoodsStockDO> goodsStockDOS;
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
     * @param goodsStockDOS 商品库存DO对象
     * @param goodsStockDAO 商品库存管理模块的DAO组件
     * @param dateProvider 日期辅助组件
     */
    public AbstractGoodsStockUpdater(List<GoodsStockDO> goodsStockDOS,GoodsStockDAO goodsStockDAO,DateProvider dateProvider){
        this.goodsStockDOS = goodsStockDOS;
        this.goodsStockDAO = goodsStockDAO;
        this.dateProvider = dateProvider;
    }
    /**
     * 更新商品库存
     * @return
     */
    @Override
    public Boolean updateGoodsStock() {
        try {
            updateSaleStockQuantity();;
            updateLockedStockQuantity();
            updateSaledStockQuantity();
            updateStockStatus();
            updateGmtModified();
            executeUpdateGoodsStock();
        }catch (Exception e){
            logger.error("error",e);
        }
        return true;
    }

    /**
     * 更新商品的销售库存
     * @throws Exception
     */
    protected abstract void updateSaleStockQuantity() throws Exception;

    /**
     * 更新商品的锁定库存
     * @throws Exception
     */
    protected abstract void updateLockedStockQuantity() throws Exception;

    /**
     * 更新商品的已销售库存
     * @throws Exception
     */
    protected abstract void updateSaledStockQuantity() throws Exception;

    /**
     * 更新商品的库存状态
     * @throws Exception
     */
    private void updateStockStatus() throws Exception{
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            if(goodsStockDO.getSaleStockQuantity() > 0L){
                goodsStockDO.setStockStatus(StockStatus.IN_STOCK);
            }else {
                goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
            }
        }
    }

    /**
     * 更新商品库存的修改时间
     */
    private void updateGmtModified() throws Exception{
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            goodsStockDO.setGmtModified(dateProvider.getCurrentTime());
        }
    }

    /**
     * 实际执行更新商品库存的操作
     * @throws Exception
     */
    private void executeUpdateGoodsStock() throws Exception{
        for (GoodsStockDO goodsStockDO : goodsStockDOS) {
            goodsStockDAO.updateGoodsStock(goodsStockDO);
        }
    }
}
