package com.gsj.www.comment.dao;

import com.gsj.www.comment.domain.CommentPictureDO;

/**
 * 评论晒图管理模块的DAO组件接口
 *
 * @author Holy
 * @create 2019 - 07 - 01 7:13
 */
public interface CommentPictureDAO {
    /**
     * 新增评论晒图
     * @param commentPictureDO 评论晒图DO对象
     */
    Long saveCommentPicture(CommentPictureDO commentPictureDO);
}
