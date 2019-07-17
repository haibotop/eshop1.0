package com.gsj.www.inventory.async;

import java.util.List;

/**
 * 离线存储管理组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 17 6:54
 */
public interface OfflineStorageManager {
    /**
     * 离线存储库存更新消息
     * @param message 库存更新消息
     * @throws Exception
     */
    void store(StockUpdateMessage message) throws Exception;

    /**
     * 获取离线存储标识
     * @return 离线存储标识
     */
    Boolean getOffline();

    /**
     * 设置离线存储标识
     * @param offline 离线存储标识
     */
    void setOffline(Boolean offline);

    /**
     * 判断是否还有下一批库存更新消息
     * @return 是否还有下一批库存更新消息
     */
    public Boolean hasNext();

    /**
     * 获取下一批库存更新消息
     * @return 下一批库存更新消息
     * @throws Exception
     */
    List<StockUpdateMessage> getNextBatch() throws Exception;

    /**
     * 批量删除库库存更新消息
     * @param stockUpdateMessages
     * @throws Exception
     */
    void removeByBatch(List<StockUpdateMessage> stockUpdateMessages) throws Exception;
}
