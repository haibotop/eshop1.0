package com.gsj.www.cart.service;

import com.gsj.www.cart.dao.ShoppingCartDAO;
import com.gsj.www.cart.dao.ShoppingCartItemDAO;
import com.gsj.www.cart.domain.ShoppingCartDO;
import com.gsj.www.cart.domain.ShoppingCartItemDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * 购物车管理模块的Service组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 06 - 16 16:00
 */
public class ShoppingCartServiceTest {

    /**
     * 购物车管理模块的service组件
     */
    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * 购物车管理模块的DAO组件
     */
    @MockBean
    private ShoppingCartDAO shoppingCartDAO;
    /**
     * 购物车条目管理的DAO组件
     */
    @MockBean
    private ShoppingCartItemDAO shoppingCartItemDAO;

    /**
     * 测试添加购物车商品条目
     * @throws Exception
     */
    @Test
    public void testAddShoppingCartItem() throws Exception{
        //准备一些参数
        Long userAccountId = 1L;
        Long goodsSkuId = 1L;

        ShoppingCartDO shoppingCartDO = createShoppingCartDO(userAccountId);
        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItemDO(userAccountId,goodsSkuId);

        //mock一下两个dao的行为
        when(shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId)).thenReturn(shoppingCartDO);
    }

    /**
     * 创建一个购物车DO对象
     * @param userAccountId 用户账号id
     * @return 购物车DO对象
     * @throws Exception
     */
    private ShoppingCartDO createShoppingCartDO(Long userAccountId) throws Exception{
        Long id = 1L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = sdf.parse(sdf.format(new Date()));

        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setId(id);
        shoppingCartDO.setUserAccountId(userAccountId);
        shoppingCartDO.setGmtCreate(currentTime);
        shoppingCartDO.setGmtModified(currentTime);

        return shoppingCartDO;
    }

    private ShoppingCartItemDO createShoppingCartItemDO(Long shoppingCartId, Long goodsSkuId) throws Exception{
         Long id = 1L;
         Long purchaseQuantity = 1L;

         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date currentTime = sdf.parse(sdf.format(new Date()));

         ShoppingCartItemDO shoppingCartItemDO = new ShoppingCartItemDO();
         shoppingCartItemDO.setId(id);
         shoppingCartItemDO.setShoppingCartId(shoppingCartId);
         shoppingCartItemDO.setGoodsSkuId(goodsSkuId);
         shoppingCartItemDO.setGmtCreate(currentTime);
         shoppingCartItemDO.setGmtModified(currentTime);

         return shoppingCartItemDO;
    }
}
