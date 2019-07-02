package com.gsj.www.comment.dao;

import com.gsj.www.comment.constant.*;
import com.gsj.www.comment.domain.CommentInfoDO;
import com.gsj.www.common.util.DateProvider;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 评论图片管理模块的DAO组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CommentInfoDAOTest {
    /**
     * 评论信息管理模块的DAO组件
     */
    @Autowired
    private CommentInfoDAO commentInfoDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增评论信息
     * @throws Exception
     */
    public void testSaveCommentInfo() throws Exception{
        CommentInfoDO commentInfoDO = createCommentInfoDO();
        Long commentInfoId = commentInfoDAO.saveCommentInfo(commentInfoDO);
        assertNotNull(commentInfoDO.getId());
        assertThat(commentInfoId,greaterThan(0L));
    }

    private CommentInfoDO createCommentInfoDO() throws Exception{
        CommentInfoDO commentInfoDO = new CommentInfoDO();

        commentInfoDO.setCommentContent("测试评论");
        commentInfoDO.setCommentStatus(CommentStatus.APPROVING);
        commentInfoDO.setCommentType(CommentType.GOOD_COMMENT);
        commentInfoDO.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfoDO.setDefaultComment(DefaultComment.NO);
        commentInfoDO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDO.setGmtModified(dateProvider.getCurrentTime());
        commentInfoDO.setGoodsId(1L);
        commentInfoDO.setGoodsScore(CommentInfoScore.FIVE);
        commentInfoDO.setGoodsSkuId(1L);
        commentInfoDO.setGoodsSkuSaleProperties("测试销售属性");
        commentInfoDO.setLogisticsScore(CommentInfoScore.FIVE);
        commentInfoDO.setOrderInfoId(1L);
        commentInfoDO.setOrderItemId(1L);
        commentInfoDO.setShowPictures(ShowPictures.YES);
        commentInfoDO.setTotalScore(CommentInfoScore.FIVE);
        commentInfoDO.setUserAccountId(1L);
        commentInfoDO.setUsername("test");

        return commentInfoDO;
    }
}
