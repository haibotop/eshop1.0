package com.gsj.www.inventory.async;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 商品库存更新队列实现类
 * @author Holy
 * @create 2019 - 07 - 16 7:22
 */
@Component
public class StockUpdateQueueImpl implements StockUpdateQueue {
    /**
     * 商品库存更新队列
     */
    private ArrayBlockingQueue<StockUpdateMessage> queue = new ArrayBlockingQueue<StockUpdateMessage>(1000);

    /**
     * 将一个消息放入队列
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void put(StockUpdateMessage message) throws Exception{
        queue.put(message);
    }

    /**
     * 将一个消息取出队列
     * @return
     * @throws Exception
     */
    @Override
    public StockUpdateMessage get() throws Exception {
        return queue.take();
    }
}
