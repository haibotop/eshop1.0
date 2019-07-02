package com.gsj.www.comment.dao;

import com.gsj.www.comment.domain.CommentAggregateDO;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
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
 * 评论统计管理模块的DAO组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 07 - 01 21:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CommentAggregateDAOTest {
    /**
     * 评论统计管理模块的DAO组件
     */
    @Autowired
    private CommentAggregateDAO commentAggregateDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增评论统计信息
     * @throws Exception
     */
    @Test
    public void testSaveCommentAggregate() throws Exception{
        Long goodsId = 1L;
        CommentAggregateDO commentAggregateDO = createCommentAggregateDO(goodsId);
        assertNotNull(commentAggregateDO.getId());
        assertThat(commentAggregateDO.getId(),greaterThan(0L));
    }

    /**
     * 创建评论统计DO对象
     * @param goodsId 商品id
     * @return 评论统计DO对象
     * @throws Exception
     */
    private CommentAggregateDO createCommentAggregateDO(Long goodsId) throws Exception{
        CommentAggregateDO commentAggregateDO = new CommentAggregateDO();
        commentAggregateDO.setBadCommentCount(1L);
        commentAggregateDO.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregateDO.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateDO.setGoodCommentCount(1L);
        commentAggregateDO.setGoodCommentRate(1.0);
        commentAggregateDO.setGoodsId(goodsId);
        commentAggregateDO.setMediumCommentCount(1L);
        commentAggregateDO.setShowPicturesCommentCount(1L);
        commentAggregateDO.setTotalCommentCount(5L);

        commentAggregateDAO.saveCommentAggregate(commentAggregateDO);

        return commentAggregateDO;
    }
}
