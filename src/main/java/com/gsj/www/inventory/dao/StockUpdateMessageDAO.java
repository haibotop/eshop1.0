package com.gsj.www.inventory.dao;

import com.gsj.www.inventory.domain.StockUpdateMessageDO;

import java.util.List;

/**
 * 库存更新消息管理模块DAO组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 17 7:37
 */
public interface StockUpdateMessageDAO {
    /**
     * 新增库存更新消息
     * @param stockUpdateMessageDO 库存更新消息DO对象
     * @return
     */
    Boolean save(StockUpdateMessageDO stockUpdateMessageDO);

    /**
     * 批量查询库存更新消息：每次50条
     * @return 库存更新消息DO对象集合
     */
    List<StockUpdateMessageDO> listByBatch();

    /**
     * 批量删除库存更新消息
     * @param ids 库存更新消息id集合字符串
     * @return 处理结果
     */
    Boolean removeByBatch(String ids);

    /**
     * 查询库存更新消息记录数
     * @return 库存更新消息记录数
     */
    Long count();
}
