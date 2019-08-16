package java.com.gsj.www.cart.dao;

import com.gsj.www.Application;
import com.gsj.www.cart.dao.ShoppingCartItemDAO;
import com.gsj.www.cart.domain.ShoppingCartItemDO;
import com.gsj.www.common.util.DateProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * 购物车条目管理模块的DAO组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 06 - 16 7:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback()
public class ShoppingCartItemDAOTest {
    /**
     * 购物车条目管理模块的DAO类
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增购物车条目
     * @throws Exception
     */
    @Test
    public void testSaveShoppingCartItem() throws Exception{
        //构造一个购物车条目出来
        Long shoppingCartId = 1L;
        Long goodsSkuId = 1L;
        Long purchaseQuantiry = 1L;

        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(shoppingCartId,goodsSkuId,purchaseQuantiry);
        Long shoppingCartItemId = shoppingCartItemDO.getId();

        assertNotNull(shoppingCartId);
        assertThat(shoppingCartItemId,greaterThan(0L));
    }

    /**
     * 测试根据商品sku id查询购物车中是否存在商品条目
     * @throws Exception
     */
    @Test
    public void testGetShoppingCartItemByGoodsSkuId() throws Exception{
        //构造一个购物车条目出来
        Long shoppingCartId = 1L;
        Long goodsSkuId = 1L;
        Long purchaseQuantiry = 1L;

        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(shoppingCartId,goodsSkuId,purchaseQuantiry);

        ShoppingCartItemDO resultShoppingCartItemDO = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartId,goodsSkuId);

        assertEquals(shoppingCartItemDO, resultShoppingCartItemDO);
    }

    /**
     * 测试更新购物车条目
     * @throws Exception
     */
    @Test
    public void testUpdateShoppingCartItem() throws Exception{
        //构造一个购物车条目出来
        Long shoppingCartId = 1L;
        Long goodsSkuId = 1L;
        Long purchaseQuantity = 1L;

        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(shoppingCartId, goodsSkuId, purchaseQuantity);

        //更新购物车条目的购买数量和修改时间
        Long newPurchaseQuantity = purchaseQuantity + 1L;

        Date currentTime = dateProvider.getCurrentTime();

        shoppingCartItemDO.setPurchaseQuantity(newPurchaseQuantity);
        shoppingCartItemDO.setGmtModified(currentTime);

        shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItemDO);

        //再次从数据库中查询购物车条目
        ShoppingCartItemDO resultShoppingCartItemDO = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartId,goodsSkuId);

        System.out.println(shoppingCartItemDAO);
        //断言比较数据是否更新
        assertEquals(newPurchaseQuantity,resultShoppingCartItemDO.getPurchaseQuantity());
        assertEquals(currentTime, resultShoppingCartItemDO.getGmtModified());
    }

    /**
     * 测试查询购物车中所有条目
     * @throws Exception
     */
    @Test
    public void testListShoppingCartItemById() throws Exception{
        Long shoppingCartId = 1L;

        Map<Long,ShoppingCartItemDO> itemMap = new HashMap<Long,ShoppingCartItemDO>();

        ShoppingCartItemDO item = null;

        item = createShoppingCartItem(shoppingCartId, 1L,3L);
        itemMap.put(item.getId(), item);

        item = createShoppingCartItem(shoppingCartId, 2L, 4L);
        itemMap.put(item.getId(), item);

        item = createShoppingCartItem(shoppingCartId, 3L, 1L);
        itemMap.put(item.getId(), item);

        //执行方法
        List<ShoppingCartItemDO> resultItems = shoppingCartItemDAO.listShoppingCartItemByCartId(shoppingCartId);

        //执行断言
        assertEquals(3,resultItems.size());

        for (ShoppingCartItemDO resultItem : resultItems) {
            ShoppingCartItemDO targetItem = itemMap.get(resultItem.getId());
            assertEquals(targetItem,resultItem);
        }
    }

    /**
     * 测试删除购物车题哦阿木
     * @throws Exception
     */
    @Test
    private void testRemove() throws Exception{
        Long shoppingCartId = 1L;
        Long goodsSkuId = 1L;
        Long purchaseQuantity = 3L;

        ShoppingCartItemDO item = createShoppingCartItem(shoppingCartId, goodsSkuId, purchaseQuantity);
        shoppingCartItemDAO.remove(item.getId());

        ShoppingCartItemDO resultItem = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartId, goodsSkuId);

        assertNull(resultItem);
    }

    /**
     * 创建一个购物车条目
     * @param shoppingCartId 购物车id
     * @param goodsSkuId 商品sku id
     * @param purchaseQuantity 购买数量
     * @return 购物车条目DO对象
     * @throws Exception
     */
    private ShoppingCartItemDO createShoppingCartItem(Long shoppingCartId,Long goodsSkuId, Long purchaseQuantity) throws Exception{
        Date currentTime = dateProvider.getCurrentTime();

        ShoppingCartItemDO shoppingCartItemDO = new ShoppingCartItemDO();
        shoppingCartItemDO.setShoppingCartId(shoppingCartId);
        shoppingCartItemDO.setGoodsSkuId(goodsSkuId);
        shoppingCartItemDO.setPurchaseQuantity(purchaseQuantity);
        shoppingCartItemDO.setGmtCreate(currentTime);
        shoppingCartItemDO.setGmtModified(currentTime);

        shoppingCartItemDAO.saveShoppingCartItem(shoppingCartItemDO);

        return shoppingCartItemDO;
    }
}
