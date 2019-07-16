package com.gsj.www.inventory.async;

import java.util.Observable;

/**
 * 商品库存更新结果观察目标
 *
 * @author Holy
 * @create 2019 - 07 - 16 7:55
 */
public class GoodsStockUpdateObservable extends Observable {
    /**
     * 消息id
     */
    private String messageId;

    /**
     * 构造函数
     * @param messageId 消息id
     */
    public GoodsStockUpdateObservable(String messageId){
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    /**
     * 设置商品库存更新结果
     * @param result 商品库存更新结果
     */
    public void setResult(Boolean result) {
        GoodsStockUpdateResult goodsStockUpdateResult = new GoodsStockUpdateResult();
        goodsStockUpdateResult.setMessageId(messageId);
        goodsStockUpdateResult.setResult(result);

        this.setChanged();
        this.notifyObservers(goodsStockUpdateResult);
    }
}
