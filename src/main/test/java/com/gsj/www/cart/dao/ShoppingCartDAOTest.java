package java.com.gsj.www.cart.dao;

import com.gsj.www.Application;
import com.gsj.www.cart.dao.ShoppingCartDAO;
import com.gsj.www.cart.domain.ShoppingCartDO;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 购物车管理模块的DAO组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 06 - 16 9:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback()
public class ShoppingCartDAOTest {
    /**
     * 购物车管理模块的DAO组件
     */
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增购物车
     * @throws Exception
     */
    @Test
    public void testSaveShoppingCart() throws Exception{
        Long userAccountId = 1L;
        ShoppingCartDO shoppingCartDO = createShoppingCart(userAccountId);

        Long shoppingCartId = shoppingCartDO.getId();

        assertNotNull(shoppingCartId);
        assertThat(shoppingCartId, greaterThan(0L));
    }

    /**
     * 测试根据账户id查询购物车
     * @throws Exception
     */
    @Test
    public void testGetShoppingCartByUserAccountId() throws Exception{
        Long userAccountId = 1L;
        ShoppingCartDO shoppingCartDO = createShoppingCart(userAccountId);

        ShoppingCartDO resultShoppingCartDO = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);

        assertEquals(shoppingCartDO,resultShoppingCartDO);
    }

    /**
     * 创建一个购物车
     * @param userAccountId
     * @return
     * @throws Exception
     */
    private ShoppingCartDO createShoppingCart(Long userAccountId) throws Exception{
        Date currentTime = dateProvider.getCurrentTime();

        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setUserAccountId(userAccountId);
        shoppingCartDO.setGmtCreate(currentTime);
        shoppingCartDO.setGmtModified(currentTime);

        shoppingCartDAO.saveShoppingCart(shoppingCartDO);

        return shoppingCartDO;
    }
}
