package com.gsj.www.commodity.dao.impl;

import com.gsj.www.commodity.dao.BrandDAO;
import com.gsj.www.commodity.domain.BrandDO;
import com.gsj.www.commodity.domain.BrandQuery;
import com.gsj.www.commodity.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 品牌管理DAO组件
 */
@Repository
public class BrandDAOImpl implements BrandDAO {
    /**
     * 品牌管理mapper组件
     */
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌集合
     */
    @Override
    public List<BrandDO> listByPage(BrandQuery query) {
        return brandMapper.listByPage(query);
    }

    /**
     * 根据id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    @Override
    public BrandDO getById(Long id) {
        return brandMapper.getById(id);
    }

    /**
     * 新增品牌
     * @param brandDO 品牌
     */
    @Override
    public void save(BrandDO brandDO) {
        brandMapper.save(brandDO);
    }

    /**
     * 更新品牌
     * @param brandDO 品牌
     */
    @Override
    public void update(BrandDO brandDO) {
        brandMapper.update(brandDO);
    }

    /**
     * 删除品牌
     * @param id 品牌id
     */
    @Override
    public void remove(Long id) {
        brandMapper.remove(id);
    }
}
