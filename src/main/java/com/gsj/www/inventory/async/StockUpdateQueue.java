package com.gsj.www.inventory.async;

/**
 * 商品库存更新消息的队列接口
 * @author Holy
 * @create 2019 - 07 - 16 7:18
 */
public interface StockUpdateQueue {
    /**
     * 将一个消息放入队列
     * @param message 消息
     * @throws Exception
     */
    void put(StockUpdateMessage message) throws Exception;

    /**
     * 直接将消息放入队列
     * @param message 消息
     * @throws Exception
     */
    void putDirect(StockUpdateMessage message) throws Exception;

    /**
     * 从队列中取出一个消息
     * @return
     * @throws Exception
     */
    StockUpdateMessage take() throws  Exception;

    /**
     * 获取队列大小
     * @return
     * @throws Exception
     */
    Integer size() throws Exception;
}
