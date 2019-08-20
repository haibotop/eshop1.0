package com.gsj.www.comment.dao;

import com.gsj.www.comment.constant.*;
import com.gsj.www.comment.domain.CommentInfoDO;
import com.gsj.www.comment.domain.CommentInfoQuery;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

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
    @Test
    public void testSaveCommentInfo() throws Exception{
        CommentInfoDO commentInfoDO = createCommentInfoDO();
        Long commentInfoId = commentInfoDAO.saveCommentInfo(commentInfoDO);
        assertNotNull(commentInfoDO.getId());
        assertThat(commentInfoId,greaterThan(0L));
    }

    public void testListByPage() throws Exception{
        //构造20条评论数据
        Map<Long, CommentInfoDO> commentInfoMap = new HashMap<>();

        for(int i = 0; i < 20; i++){
            CommentInfoDO commentInfo = createCommentInfoDO();
            commentInfoMap.put(commentInfo.getId(), commentInfo);
        }

        //执行分页查询
        Integer offset = 15;
        Integer size = 5;
        CommentInfoQuery query = new CommentInfoQuery();
        query.setCommnetStatus(CommentStatus.APPROVING);
        query.setCommentType(null);
        query.setDefaultComment(DefaultComment.NO);
        query.setStartTime(dateProvider.getCurrentTime());
        query.setEndTime(dateProvider.getCurrentTime());
        query.setOffset(offset);
        query.setSize(size);
        query.setShowPictures(ShowPictures.YES);
        query.setTotalScore(CommentInfoScore.FIVE);

        List<CommentInfoDO> resultComments = commentInfoDAO.listByPage(query);

        //执行断言
        assertEquals((int)size, resultComments.size());

        for(CommentInfoDO resultComment : resultComments){
            assertEquals(commentInfoMap.get(resultComment.getId()), resultComment);
        }
    }

    /**
     * 测试根据id查询评论信息
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
        CommentInfoDO comment = createCommentInfoDO();
        CommentInfoDO resultComment = commentInfoDAO.getById(comment.getId());
        assertEquals(comment, resultComment);
    }

    /**
     * 测试更新评论
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        CommentInfoDO comment = createCommentInfoDO();

        comment.setCommentStatus(CommentStatus.APPROVED);
        comment.setGmtModified(dateProvider.getCurrentTime());
        commentInfoDAO.update(comment);

        CommentInfoDO resultComment = commentInfoDAO.getById(comment.getId());

        assertEquals(comment, resultComment);
    }

    /**
     * 测试删除评论
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception {
        CommentInfoDO comment = createCommentInfoDO();
        commentInfoDAO.remove(comment.getId());
        CommentInfoDO resultComment = commentInfoDAO.getById(comment.getId());
        assertNull(resultComment);
    }

    /**
     * 创建评论信息
     * @return
     * @throws Exception
     */
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
