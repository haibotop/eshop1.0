package com.gsj.www.comment.mapper;

import com.gsj.www.comment.domain.CommentPictureDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 评论晒图管理模块的mapper组件
 *
 * @author Holy
 * @create 2019 - 07 - 01 6:18
 */
@Mapper
public interface CommentPictureMapper {
    /**
     * 新增评论晒图
     * @param commentPictureDO 评论晒图DO对象
     */
    @Insert("INSERT INTO comment_picture("
            + "comment_info_id,"
            + "comment_picture_path,"
            + "gmt_create,"
            + "gmt_modified"
            + ")"
            + "VALUES("
            + "#{commentInfoId},"
            + "#{commentPicturePath},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void saveCommentPicture(CommentPictureDO commentPictureDO);
}
