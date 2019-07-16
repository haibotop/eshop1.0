package com.gsj.www.inventory.async;

/**
 * 商品库存更新结果
 * @author Holy
 * @create 2019 - 07 - 16 21:36
 */
public class GoodsStockUpdateResult {
    /**
     * 商品库存更新消息id
     */
    private String messageId;
    /**
     * 商品库存更新结果
     */
    private Boolean result;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
