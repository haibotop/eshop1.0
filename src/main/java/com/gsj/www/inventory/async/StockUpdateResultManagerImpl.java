package com.gsj.www.inventory.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 商品库存更新结果管理组件实现类
 *
 * @author Holy
 * @create 2019 - 07 - 17 6:44
 */
@Component
public class StockUpdateResultManagerImpl implements StockUpdateResultManager {
    /**
     * 商品缓存更新结果
     */
    private Map<String,StockUpdateObservable> observableMap = new ConcurrentHashMap<String, StockUpdateObservable>();
    /**
     * 商品库存更新结果观察者
     */
    @Autowired
    private StockUpdateObserver observer;

    /**
     * 设置对商品库存更新结果的观察
     * @param messageId 消息id
     */
    @Override
    public void observe(String messageId) {
        StockUpdateObservable observable = new StockUpdateObservable(messageId);
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
        StockUpdateObservable observable = observableMap.get(messageId);
        observable.setResult(resultl);
        observableMap.remove(messageId);
    }
}
