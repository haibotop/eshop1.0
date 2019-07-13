package com.gsj.www.inventory.updater;

/**
 * 库存更新命令工厂接口
 */
public interface GoodsStockUpdaterFactory<T> {
    /**
     * 创建一个库存更新命令
     * @param parameter 参数对象
     * @return 库存更新命令
     */
    GoodsStockUpdater create(T parameter);
}
