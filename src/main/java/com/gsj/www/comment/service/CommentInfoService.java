package com.gsj.www.comment.service;

import com.gsj.www.comment.domain.CommentInfoDTO;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.order.domain.OrderOrderDTO;

/**
 * 评论信息管理模块的service组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 30 22:39
 */
public interface CommentInfoService {
    /**
     * 新增手动发表的评论信息
     * @param commentInfoDTO
     * @return
     */
    Boolean saveManualPublishedCommentInfo(CommentInfoDTO commentInfoDTO);

    /**
     * 新增自动发表的评论信息
     * @param orderOrderDTO 订单信息DTO对象
     * @param orderItemDTO 订单条目DTO对象
     * @return 处理结果
     */
    CommentInfoDTO saveAutoPublishedCommentInfo(OrderOrderDTO orderOrderDTO, OrderItemDTO orderItemDTO);
}
