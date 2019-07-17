package com.gsj.www.inventory.async;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.dao.StockUpdateMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 离线存储管理组件实现类
 *
 * @author Holy
 * @create 2019 - 07 - 17 7:06
 */
@Component
public class OfflineStorageManagerImpl implements OfflineStorageManager {
    /**
     * 库存更新消息管理模块DAO组件
     */
    @Autowired
    private StockUpdateMessageDAO stockUpdateMessageDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    @Override
    public void store(StockUpdateMessage message) throws Exception {

    }

    @Override
    public Boolean getOffline() {
        return null;
    }

    @Override
    public void setOffline(Boolean offline) {

    }

    @Override
    public Boolean hasNext() {
        return null;
    }

    @Override
    public List<StockUpdateMessage> getNextBatch() throws Exception {
        return null;
    }

    @Override
    public void removeByBatch(List<StockUpdateMessage> stockUpdateMessages) throws Exception {

    }
}
