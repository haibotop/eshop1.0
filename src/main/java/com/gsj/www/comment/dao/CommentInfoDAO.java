package com.gsj.www.comment.dao;

import com.gsj.www.comment.domain.CommentInfoDO;

/**
 * 评论信息管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 01 7:12
 */
public interface CommentInfoDAO {
    /**
     * 新增评论信息
     * @param commentInfoDO 评论信息DO对象
     */
    Long saveCommentInfo(CommentInfoDO commentInfoDO);
}
