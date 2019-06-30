package com.gsj.www.comment.dao;

import com.gsj.www.comment.domain.CommentAggregateDO;

/**
 * 评论统计信息管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 01 7:09
 */
public interface CommentAggregateDAO {
    /**
     * 根据商品id查询评论统计信息
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId);

    /**
     * 新增评论统计信息
     * @param commentAggregateDO 评论统计信息DO对象
     */
    Boolean saveCommentAggregate(CommentAggregateDO commentAggregateDO);

    /**
     * 更新评论统计信息
     * @param commentAggregateDO 评论统计信息DO对象
     */
    Boolean updateCommentAggregate(CommentAggregateDO commentAggregateDO);
}
