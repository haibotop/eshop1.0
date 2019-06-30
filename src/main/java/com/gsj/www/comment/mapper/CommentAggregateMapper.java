package com.gsj.www.comment.mapper;

import com.gsj.www.comment.domain.CommentAggregateDO;
import org.apache.ibatis.annotations.*;

/**
 * 评论统计信息管理模块的mapper组建
 */
@Mapper
public interface CommentAggregateMapper {
    /**
     * 根据商品id查询评论统计信息
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    @Select("SELECT " +
            "id," +
            "goods_id," +
            "total_comment_count," +
            "good_comment_count," +
            "good_comment_rate," +
            "show_pictures_comment_count," +
            "medium_comment_count," +
            "bad_comment_count," +
            "gmt_create," +
            "gmt_modified " +
            "from comment_aggregate " +
            "where goods_id=#{goodsId}")
    @Results({
       @Result(column = "id", property = "id",id=true),
       @Result(column = "goods_id",property = "goodsId"),
       @Result(column = "total_comment_count", property = "totalCommentCount"),
       @Result(column = "good_comment_count", property = "goodCommentCount"),
       @Result(column = "good_comment_rate", property = "goodCommentRate"),
       @Result(column = "show_pictures_comment_count", property = "showPictureCommentCount"),
       @Result(column = "medium_comment_count", property = "mediumCommentCount"),
       @Result(column = "bad_comment_count", property = "badCommentCount"),
       @Result(column = "gmt_create", property = "gmtCreate"),
       @Result(column = "gmt_modified", property = "gmtModified")
    })
    CommentAggregateDO getCommentAggregateByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 新增评论统计信息
     * @param commentAggregateDO 评论统计信息DO对象
     */
    @Insert("insert into comment_aggregate(" +
            "goods_id," +
            "total_comment_count," +
            "good_comment_count," +
            "good_comment_rate," +
            "show_pictures_comment_count," +
            "medium_comment_count," +
            "bad_comment_count," +
            "gmt_create," +
            "gmt_modified" +
            ") values(" +
            "#{goodsId}," +
            "#{totalCommentCount}," +
            "#{goodCommentCount}," +
            "#{goodCommentRate}," +
            "#{showPicturesCommentCount}," +
            "#{mediumCommentCount}," +
            "#{badCommentCount}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveCommentAggregate(CommentAggregateDO commentAggregateDO);

    /**
     * 更新评论统计信息
     * @param commentAggregateDO
     */
    @Update("update comment_aggregate set " +
            "goods_id=#{goodsId}," +
            "total_comment_count=#{totalCommentCount}," +
            "good_comment_count=#{goodCommentCount}," +
            "good_comment_rate=#{goodCommentRate}," +
            "show_pictures_comment_count=#{showPicturesCommentCount}," +
            "medium_comment_count=#{mediumCommentCount}," +
            "bad_comment_count=#{badCommentCount}," +
            "gmt_create=#{gmtCreate}," +
            "gmt_modified=#{gmtModified} " +
            "where id=#{id}")
    void updateCommentAggregate(CommentAggregateDO commentAggregateDO);
}
