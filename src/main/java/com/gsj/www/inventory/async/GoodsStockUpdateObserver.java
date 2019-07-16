package com.gsj.www.inventory.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

/**
 * 商品库存更新结果观察者
 *
 * @author Holy
 * @create 2019 - 07 - 16 21:50
 */
@Component
public class GoodsStockUpdateObserver implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(GoodsStockUpdateObserver.class);

    /**
     * 通知异步处理结果
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        GoodsStockUpdateResult goodsStockUpdateResult = (GoodsStockUpdateResult) arg;
        logger.info("商品库存更新消息【messageId="+goodsStockUpdateResult.getMessageId()
                +"】"+"的异步处理结果为："+goodsStockUpdateResult.getResult());
    }
}
