package com.gsj.www.inventory.dao.impl;

import com.gsj.www.inventory.dao.StockUpdateMessageDAO;
import com.gsj.www.inventory.domain.StockUpdateMessageDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 库存更新消息管理模块DAO组件实现类
 *
 * @author Holy
 * @create 2019 - 07 - 17 7:39
 */
@Repository
public class StockUpdateMessageDAOImpl implements StockUpdateMessageDAO {
    private static final Logger logger = LoggerFactory.getLogger(StockUpdateMessageDAOImpl.class);



    @Override
    public Boolean save(StockUpdateMessageDO stockUpdateMessageDO) {
        return null;
    }

    @Override
    public List<StockUpdateMessageDO> listByBatch() {
        return null;
    }

    @Override
    public Boolean removeByBatch(String ids) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
