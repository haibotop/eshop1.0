package com.gsj.www.commodity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商品管理mapper组件
 */
@Mapper
public interface GoodsMapper {
    /**
     * 根据类目id查询商品数量
     * @param categoryId
     * @return
     */
    @Select("SELECT count(*) FROM commodity_goods WHERE category_id=#{categoryId}")
    Long countByCategoryId(@Param("categoryId") Long categoryId);
}
