package com.gsj.www.comment.dao.impl;

import com.gsj.www.comment.dao.CommentInfoDAO;
import com.gsj.www.comment.domain.CommentInfoDO;
import com.gsj.www.comment.mapper.CommentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 评论信息管理模块的DAO组件
 *
 * @author Holy
 * @create 2019 - 07 - 01 7:19
 */
@Repository
public class CommentInfoDAOImpl implements CommentInfoDAO{
    private static final Logger logger = LoggerFactory.getLogger(CommentInfoDAOImpl.class);

    /**
     * 评论信息管理模块的mapper组件
     */
    @Autowired
    private CommentInfoMapper commentInfoMapper;

    /**
     * 新增评论信息
     * @param commentInfoDO 评论信息DO对象
     */
    @Override
    public Long saveCommentInfo(CommentInfoDO commentInfoDO) {
        try {
            commentInfoMapper.saveCommentInfo(commentInfoDO);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return commentInfoDO.getId();
    }
}
