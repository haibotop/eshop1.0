package com.gsj.www.inventory.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 商品库存更新队列实现类
 * @author Holy
 * @create 2019 - 07 - 16 7:22
 */
@Component
public class StockUpdateQueueImpl implements StockUpdateQueue {
    private static final Integer QUEUE_MAX_SIZE = 1000;
    /**
     * 离线存储管理组件
     */
    @Autowired
    private OfflineStorageManager offlineStorageManager;

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
        //每次要往内存队列放消息之前，先检查一下离线存储标识
        //如果出发了离线存储，直接往离线存储去写入，不要走后边的逻辑了
        //写完离线存储之后，需要检查一下内存队列大大小，如果内存队列已经清零，则启动一个后台线程
        //让后台线程去将离线存储中的数据恢复写入内存队列中
        if(offlineStorageManager.getOffline()){
            offlineStorageManager.store(message);

            if(queue.size() == 0){
                new OfflineResumeThread(offlineStorageManager, this).start();
            }
            return;
        }

        //如果内存队列已经满了，此时就触发离线存储
        if(QUEUE_MAX_SIZE.equals(queue.size())){
            offlineStorageManager.store(message);
            offlineStorageManager.setOffline(true);
            return;
        }
        queue.put(message);
    }

    /**
     * 直接将消息放入队列
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void putDirect(StockUpdateMessage message) throws Exception {
        queue.put(message);
    }

    /**
     * 将一个消息取出队列
     * @return
     * @throws Exception
     */
    @Override
    public StockUpdateMessage take() throws Exception {
        return queue.take();
    }

    @Override
    public Integer size() throws Exception {
        return queue.size();
    }
}
