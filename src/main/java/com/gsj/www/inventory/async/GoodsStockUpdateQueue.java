package com.gsj.www.inventory.async;

/**
 * 商品库存更新消息的队列接口
 * @author Holy
 * @create 2019 - 07 - 16 7:18
 */
public interface GoodsStockUpdateQueue {
    /**
     * 将一个消息放入队列
     * @param message 消息
     * @throws Exception
     */
    public void  put(GoodsStockUpdateMessage message) throws Exception;

    /**
     * 从队列中去除一个消息
     * @return
     * @throws Exception
     */
    public GoodsStockUpdateMessage get() throws  Exception;
}
