package com.gsj.www.inventory.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 商品库存更新结果管理组件
 *
 * @author Holy
 * @create 2019 - 07 - 16 21:44
 */
@Component
public class GoodsStockUpdateManagerImpl implements GoodsStockUpdateManage{
    /**
     * 商品库存更新结果map
     */
    private Map<String, GoodsStockUpdateObservable> observableMap = new ConcurrentHashMap<String, GoodsStockUpdateObservable>();
    /**
     * 商品库存更新结果观察者
     */
    @Autowired
    private GoodsStockUpdateObserver observer;

    /**
     * 设置对商品库存更新结果的观察
     * @param messageId 消息id
     */
    @Override
    public void observe(String messageId) {
        GoodsStockUpdateObservable observable = new GoodsStockUpdateObservable(messageId);
        observable.addObserver(observer);
        observableMap.put(messageId,observable);
    }

    /**
     * 获取商品库存更新结果的观察目标
     * @param messageId 商品库存更新消息id
     * @param resultl 商品库存更新结果的观察目标
     */
    @Override
    public void inform(String messageId, Boolean resultl) {
        GoodsStockUpdateObservable observable = observableMap.get(messageId);
        observable.setResult(resultl);
        observableMap.remove(messageId);
    }
}
