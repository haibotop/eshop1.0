package com.gsj.www.comment.service.impl;

import com.gsj.www.comment.constant.CommentType;
import com.gsj.www.comment.constant.ShowPictures;
import com.gsj.www.comment.dao.CommentAggregateDAO;
import com.gsj.www.comment.domain.CommentAggregateDO;
import com.gsj.www.comment.domain.CommentAggregateDTO;
import com.gsj.www.comment.domain.CommentInfoDTO;
import com.gsj.www.comment.service.CommentAggregateService;
import com.gsj.www.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * 评论统计信息管理模块的service组件
 */
@Service
public class CommentAggregateServiceImpl implements CommentAggregateService{

    private static final Logger logger = LoggerFactory.getLogger(CommentAggregateServiceImpl.class);
    /**
     * 评论统计信息管理模块的DAO组件
     */
    @Autowired
    private CommentAggregateDAO commentAggregateDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 更新评论统计信息
     * @param commentInfoDTO 评论信息
     * @return 处理结果
     */
    @Override
    public CommentAggregateDO refreshCommentAggregate(CommentInfoDTO commentInfoDTO) {
        CommentAggregateDO commentAggregateDO = null;
        try{
            commentAggregateDO = commentAggregateDAO.getCommentAggregateByGoodsId(commentInfoDTO.getGoodsId());
            if(commentAggregateDO == null){
                commentAggregateDO = saveCommentAggregate(commentInfoDTO);
            }else {
                updateCommentAggregate(commentInfoDTO, commentAggregateDO);
            }
        }catch (Exception e){
            logger.info("error",e);
            return null;
        }
        return commentAggregateDO;
    }

    /**
     * 根据商品id查询评论统计信息
     * @param goodsId 商品id
     * @return 评论统计信息
     * @throws Exception
     */
    @Override
    public CommentAggregateDTO getCommentAggregateByGoodsId(Long goodsId) throws Exception {
        return commentAggregateDAO.getCommentAggregateByGoodsId(goodsId).clone(CommentAggregateDTO.class);
    }

    /**
     * 新增评论统计信息
     * @param commentInfoDTO
     * @return
     * @throws Exception
     */
    private CommentAggregateDO saveCommentAggregate(CommentInfoDTO commentInfoDTO) throws Exception{
        CommentAggregateDO commentAggregateDO = new CommentAggregateDO();

        commentAggregateDO.setGoodsId(commentInfoDTO.getGoodsId());
        commentAggregateDO.setTotalCommentCount(1L);
        commentAggregateDO.setGoodCommentCount(1L);
        commentAggregateDO.setMediumCommentCount(0L);
        commentAggregateDO.setBadCommentCount(0L);

        if(commentInfoDTO.getCommentType().equals(CommentType.GOOD_COMMENT)){
            commentAggregateDO.setGoodCommentCount(1L);
        }else if(commentInfoDTO.getCommentType().equals(CommentType.MEDIUM_COMMENT)){
            commentAggregateDO.setMediumCommentCount(1L);
        }else if(commentInfoDTO.getCommentType().equals(CommentType.BAD_COMMENT)){
            commentAggregateDO.setBadCommentCount(1L);
        }

        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(
                (double) commentAggregateDO.getGoodCommentCount() / (double)commentAggregateDO.getTotalCommentCount()));
        commentAggregateDO.setGoodCommentRate(goodCommentRate);

        if(ShowPictures.YES.equals(commentInfoDTO.getShowPictures())){
            commentAggregateDO.setShowPicturesCommentCount(1L);
        }

        commentAggregateDO.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregateDO.setGmtModified(dateProvider.getCurrentTime());

        commentAggregateDAO.saveCommentAggregate(commentAggregateDO);

        return commentAggregateDO;
    }

    /**
     * 更新评论统计信息
     * @param commentInfoDTO
     * @param commentAggregateDO
     * @throws Exception
     */
    private void updateCommentAggregate(CommentInfoDTO commentInfoDTO,CommentAggregateDO commentAggregateDO) throws Exception{
        commentAggregateDO.setTotalCommentCount(commentAggregateDO.getTotalCommentCount() + 1L);

        if(commentInfoDTO.getCommentType().equals(CommentType.GOOD_COMMENT)){
            commentAggregateDO.setGoodCommentCount(commentAggregateDO.getGoodCommentCount() + 1L);
        }else if(commentInfoDTO.getCommentType().equals(CommentType.MEDIUM_COMMENT)){
            commentAggregateDO.setMediumCommentCount(commentAggregateDO.getMediumCommentCount() + 1l);
        }else if(commentInfoDTO.getCommentType().equals(CommentType.BAD_COMMENT)){
            commentAggregateDO.setBadCommentCount(commentAggregateDO.getBadCommentCount() + 1L);
        }

        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format((double)commentAggregateDO
        .getGoodCommentCount() / (double)commentAggregateDO.getTotalCommentCount()));
        commentAggregateDO.setGoodCommentRate(goodCommentRate);

        if(ShowPictures.YES.equals(commentInfoDTO.getShowPictures())){
            commentAggregateDO.setShowPicturesCommentCount(commentAggregateDO.getShowPicturesCommentCount() + 1l);
        }

        commentAggregateDO.setGmtModified(dateProvider.getCurrentTime());

        commentAggregateDAO.updateCommentAggregate(commentAggregateDO);
    }


}
