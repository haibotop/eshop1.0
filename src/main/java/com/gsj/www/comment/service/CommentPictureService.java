package com.gsj.www.comment.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 评论晒图管理模块的service组件
 *
 * @author Holy
 * @create 2019 - 06 - 30 22:37
 */
public interface CommentPictureService {
    /**
     * 保存评论晒图
     * @param appBasePath 当前应用根路径
     * @param commentInfoId 评论信息id
     * @param files 评论晒图
     * @return 处理结果
     */
    Boolean saveCommentPicture(String appBasePath, Long commentInfoId, MultipartFile[] files);
}
