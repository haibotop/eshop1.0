package com.gsj.www.comment.controller;

import com.gsj.www.comment.constant.ShowPictures;
import com.gsj.www.comment.domain.CommentInfoDTO;
import com.gsj.www.comment.domain.CommentInfoVO;
import com.gsj.www.comment.service.CommentAggregateService;
import com.gsj.www.comment.service.CommentInfoService;
import com.gsj.www.comment.service.CommentPictureService;
import com.gsj.www.membership.service.MembershipFacadeService;
import com.gsj.www.order.service.OrderFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论管理模块的controller组件
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    /**
     * 评论信息遍历模块的service租价
     */
    @Autowired
    private CommentInfoService commentInfoService;
    /**
     * 评论晒图管理模块的service组件
     */
    @Autowired
    private CommentPictureService commentPictureService;
    /**
     * 评论统计信息管理模块的service组件
     */
    @Autowired
    private CommentAggregateService commentAggregateService;
    /**
     * 订单中心的service组件
     */
    @Autowired
    private OrderFacadeService orderFacadeService;
    /**
     * 会员中心的service组件
     */
    @Autowired
    private MembershipFacadeService membershipFacadeService;

    public Boolean publishComment(HttpServletRequest request, CommentInfoVO commentInfoVO, MultipartFile[] files){
        try {
            //为评论设置是否晒图
            Integer showPictures = ShowPictures.NO;

            if(files != null && files.length > 0){
                for (MultipartFile file : files) {
                    if(file != null){
                        showPictures = ShowPictures.YES;
                        break;
                    }
                }
            }
            commentInfoVO.setShowPictures(showPictures);

            //保存评论信息
            CommentInfoDTO commentInfoDTO = commentInfoVO.clone(CommentInfoDTO.class);
            commentInfoService.saveManualPublishedCommentInfo(commentInfoDTO);

            //上传评论晒图图片
            String appBasePath = request.getSession().getServletContext().getRealPath("/");
            commentPictureService.saveCommentPicture(appBasePath,commentInfoDTO.getId(),files);

            //更新评论统计信息
            commentAggregateService.refreshCommentAggregate(commentInfoDTO);

            //通知订单中心订单已经发表了评论
            orderFacadeService.informPublishCommentEvent(commentInfoDTO.getOrderInfoId());

            //通知会员中心用户已经发表了评论
            membershipFacadeService.informPublishCommentEvent(commentInfoDTO.getUserAccountId(),ShowPictures.YES.equals(showPictures));
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }
}
