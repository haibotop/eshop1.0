package com.gsj.www.commodity.dao;

import com.gsj.www.commodity.domain.BrandDO;
import com.gsj.www.commodity.domain.BrandQuery;

import java.util.List;

/**
 * 品牌管理DAO组件接口
 * @author holy
 */
public interface BrandDAO {
    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌集合
     */
    List<BrandDO> listByPage(BrandQuery query);

    /**
     * 根据id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    BrandDO getById(Long id);

    /**
     * 新增品牌
     * @param brandDO 品牌
     */
    void save(BrandDO brandDO);

    /**
     * 更新品牌
     * @param brandDO 品牌
     */
    void update(BrandDO brandDO);

    /**
     * 删除品牌
     * @param id 品牌id
     */
    void remove(Long id);
}
