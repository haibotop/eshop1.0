package com.gsj.www.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 评论统计信息管理模块的mapper组建
 */
@Mapper
public interface CommentAggregateDO {
    /**
     * 根据商品id查询评论统计信息
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    CommentAggregateDO getCommentAggregateByGoodsId(@Param("goodsId") Long goodsId);
}
