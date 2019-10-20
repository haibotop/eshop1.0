package com.gsj.www.comment.service;

import com.gsj.www.comment.domain.CommentAggregateDO;
import com.gsj.www.comment.domain.CommentAggregateDTO;
import com.gsj.www.comment.domain.CommentInfoDTO;

/**
 * 评论统计信息管理模块的service组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 30 22:43
 */
public interface CommentAggregateService {
    /**
     * 更新评论统计信息
     * @param commentInfoDTO 评论信息
     * @return 处理结果
     */
    CommentAggregateDO refreshCommentAggregate(CommentInfoDTO commentInfoDTO);

    /**
     * 根据商品id查询评论统计信息
     * @param goodsId 商品id
     * @return 评论统计信息
     * @throws Exception
     */
    CommentAggregateDTO getCommentAggregateByGoodsId(Long goodsId) throws Exception;
}
