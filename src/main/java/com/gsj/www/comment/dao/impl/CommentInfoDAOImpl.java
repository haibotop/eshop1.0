package com.gsj.www.comment.dao.impl;

import com.gsj.www.comment.dao.CommentInfoDAO;
import com.gsj.www.comment.domain.CommentInfoDO;
import com.gsj.www.comment.domain.CommentInfoQuery;
import com.gsj.www.comment.mapper.CommentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 分页查询评论信息
     * @param query 评论查询条件
     * @return 评论信息
     */
    @Override
    public List<CommentInfoDO> listByPage(CommentInfoQuery query) {
        try {
            return commentInfoMapper.listByPage(query);
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 根据id查询评论信息
     * @param id 评论信息id
     * @return 评论信息
     */
    @Override
    public CommentInfoDO getById(Long id) {
        try {
            return commentInfoMapper.getById(id);
        }catch (Exception e){
            logger.error("error",e);
            return null;
        }
    }

    /**
     * 更新评论
     * @param comment 评论信息
     */
    public Boolean update(CommentInfoDO comment) {
        try {
            commentInfoMapper.update(comment);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除评论
     * @param id 评论id
     */
    public Boolean remove(Long id) {
        try {
            commentInfoMapper.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
