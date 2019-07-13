package com.gsj.www.comment.schedule;

import com.gsj.www.comment.domain.CommentInfoDTO;
import com.gsj.www.comment.service.CommentAggregateService;
import com.gsj.www.comment.service.CommentInfoService;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.order.domain.OrderOrderDTO;
import com.gsj.www.order.service.OrderFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动发表评论的调度任务
 */
@Component
public class AutoPublishCommentTask {
    private static final Logger logger = LoggerFactory.getLogger(AutoPublishCommentTask.class);
    /**
     *订单中心的service组件
     */
    @Autowired
    private OrderFacadeService orderFacadeService;
    /**
     * 评论信息管理模块的service组件
     */
    @Autowired
    private CommentInfoService commentInfoService;
    /**
     * 评论统计信息管理模块的service组件
     */
    @Autowired
    private CommentAggregateService commentAggregateService;

    /**
     * 每隔10分钟检查一次
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void execute(){
        try {
            //先从订单中心查询确认时间超过时间超过7天，而且还没有发表评论的订单
            List<OrderOrderDTO> orderOrderDTOS = orderFacadeService.listNotPublishedCommentOrders();
            List<Long> orderInfoIds = new ArrayList<Long>(orderOrderDTOS.size());

            if(orderOrderDTOS != null && orderOrderDTOS.size() > 0){
                //遍历所有的订单
                for (OrderOrderDTO orderOrderDTO : orderOrderDTOS) {
                    if(orderOrderDTO.getOrderItemDTOList() == null && orderOrderDTO.getOrderItemDTOList().size() == 0){
                        continue;
                    }

                    orderInfoIds.add(orderOrderDTO.getId());

                    //遍历订单中的订单项
                    for (OrderItemDTO orderItemDTO : orderOrderDTO.getOrderItemDTOList()) {
                        //先保存自动发表的评论信息
                        CommentInfoDTO commentInfoDTO = commentInfoService.saveAutoPublishedCommentInfo(orderOrderDTO,orderItemDTO);

                        //更新评论统计信息
                        commentAggregateService.refreshCommentAggregate(commentInfoDTO);
                    }
                }
            }
            //通知订单中心，批量发表了评论
            orderFacadeService.informBatchPublishCommentEvent(orderInfoIds);
        }catch (Exception e){
            logger.error("error",e);
        }
    }
}
