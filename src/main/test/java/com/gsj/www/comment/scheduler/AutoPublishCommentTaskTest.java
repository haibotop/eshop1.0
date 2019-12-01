package com.gsj.www.comment.scheduler;

import com.gsj.www.comment.domain.CommentInfoDTO;
import com.gsj.www.comment.schedule.AutoPublishCommentTask;
import com.gsj.www.comment.service.CommentAggregateService;
import com.gsj.www.comment.service.CommentInfoService;
import com.gsj.www.order.domain.OrderItemDTO;
import com.gsj.www.order.domain.OrderInfoDTO;
import com.gsj.www.order.service.OrderFacadeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 自动发表评论调度任务的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoPublishCommentTaskTest {
    /**
     * 自动发表评论调度任务
     */
    @Autowired
    private AutoPublishCommentTask autoPubliashCommentTask;
    /**
     * 订单中心对外接口service组件
     */
    @MockBean
    private OrderFacadeService orderFacadeService;
    /**
     * 评论信息管理模块service组件
     */
    @MockBean
    private CommentInfoService commentInfoService;
    /**
     * 评论统计信息管理模块service组件
     */
    @MockBean
    private CommentAggregateService commentAggregateService;

    /**
     * 测试每隔10分钟检查一次
     * @throws Exception
     */
    @Test
    public void testExecute() throws Exception {
        List<OrderInfoDTO> orderInfoDTOS = createOrderOrderDTOs();

        List<Long> orderInfoIds = new ArrayList<Long>();
        for(OrderInfoDTO orderInfoDTO : orderInfoDTOS) {
            orderInfoIds.add(orderInfoDTO.getId());
        }

        when(orderFacadeService.listNotPublishedCommentOrders()).thenReturn(orderInfoDTOS);

        CommentInfoDTO commentInfoDTO1 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOS.get(0),
                orderInfoDTOS.get(0).getOrderItemDTOList().get(0))).thenReturn(commentInfoDTO1);

        CommentInfoDTO commentInfoDTO2 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOS.get(0),
                orderInfoDTOS.get(0).getOrderItemDTOList().get(1))).thenReturn(commentInfoDTO2);

        CommentInfoDTO commentInfoDTO3 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOS.get(1),
                orderInfoDTOS.get(1).getOrderItemDTOList().get(0))).thenReturn(commentInfoDTO3);

        CommentInfoDTO commentInfoDTO4 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOS.get(1),
                orderInfoDTOS.get(1).getOrderItemDTOList().get(1))).thenReturn(commentInfoDTO4);

        autoPubliashCommentTask.execute();

        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOS.get(0),
                orderInfoDTOS.get(0).getOrderItemDTOList().get(0));
        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOS.get(0),
                orderInfoDTOS.get(0).getOrderItemDTOList().get(1));
        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOS.get(1),
                orderInfoDTOS.get(1).getOrderItemDTOList().get(0));
        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOS.get(1),
                orderInfoDTOS.get(1).getOrderItemDTOList().get(1));
        verify(commentAggregateService, times(4)).refreshCommentAggregate(commentInfoDTO1);
        verify(orderFacadeService, times(1)).informBatchPublishCommentEvent(orderInfoIds);
    }

    /**
     * 创建订单信息DTO集合
     * @return 订单信息DTO集合
     * @throws Exception
     */
    private List<OrderInfoDTO> createOrderOrderDTOs() throws Exception {
        // 构造第一个订单信息DTO
        OrderInfoDTO orderInfoDTO1 = new OrderInfoDTO();
        orderInfoDTO1.setId(1L);

        OrderItemDTO orderItemDTO1 = new OrderItemDTO();
        orderItemDTO1.setId(1L);

        OrderItemDTO orderItemDTO2 = new OrderItemDTO();
        orderItemDTO2.setId(2L);

        List<OrderItemDTO> orderItemDTOs1 = new ArrayList<OrderItemDTO>();
        orderItemDTOs1.add(orderItemDTO1);
        orderItemDTOs1.add(orderItemDTO2);

        orderInfoDTO1.setOrderItemDTOList(orderItemDTOs1);

        // 构造第二个订单信息DTO
        OrderInfoDTO orderInfoDTO2 = new OrderInfoDTO();
        orderInfoDTO2.setId(2L);

        OrderItemDTO orderItemDTO3 = new OrderItemDTO();
        orderItemDTO3.setId(3L);

        OrderItemDTO orderItemDTO4 = new OrderItemDTO();
        orderItemDTO4.setId(4L);

        List<OrderItemDTO> orderItemDTOs2 = new ArrayList<OrderItemDTO>();
        orderItemDTOs2.add(orderItemDTO3);
        orderItemDTOs2.add(orderItemDTO4);

        orderInfoDTO2.setOrderItemDTOList(orderItemDTOs2);

        // 构造订单DTO集合
        List<OrderInfoDTO> orderInfoDTOS = new ArrayList<OrderInfoDTO>();
        orderInfoDTOS.add(orderInfoDTO1);
        orderInfoDTOS.add(orderInfoDTO2);

        return orderInfoDTOS;
    }
}
