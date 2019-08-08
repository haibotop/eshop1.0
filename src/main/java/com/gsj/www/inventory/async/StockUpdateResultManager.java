package com.gsj.www.inventory.async;

/**
 * 商品库存更新结果管理组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 16 21:39
 */
public interface StockUpdateResultManager {

    /**
     * 设置对商品库存更新结果的观察
     * @param messageId 消息id
     */
    void observe(String messageId);

    /**
     * 获取商品库存更新结果的观察目标
     * @param messageId 商品库存更新消息id
     * @param resultl 商品库存更新结果的观察目标
     */
    void inform(String messageId, Boolean resultl);

    /**
     * 获取库存更新结果观察目标
     * @param messageId 消息id
     * @return
     */
    StockUpdateObservable getObservable(String messageId);
}
