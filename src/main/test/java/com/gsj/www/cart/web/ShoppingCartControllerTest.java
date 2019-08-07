package java.com.gsj.www.cart.web;

import com.alibaba.fastjson.JSONObject;
import com.gsj.www.cart.controller.ShoppingCartController;
import com.gsj.www.cart.domain.AddShoppingCartItemQuery;
import com.gsj.www.cart.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * 购物车管理模块的controller组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 06 - 17 22:46
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {
    private Logger logger = LoggerFactory.getLogger(ShoppingCartControllerTest.class);
    /**
     * mvc测试类
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * 购物车管理模块的service组件
     */
    @MockBean
    private ShoppingCartService shoppingCartService;

    @PostMapping("/item/add")
    public Boolean addShoppingCartItem(@RequestBody AddShoppingCartItemQuery query){
        try {
            return shoppingCartService.addShoppingCartItem(query.getUserAccountId(),query.getGoodsSkuId());
        }catch (Exception e){
            logger.error("error",e);
        }
        return false;
    }

    /**
     * 测试添加购物车商品条目
     * @throws Exception
     */
    @Test
    public void testAddShoppingCartItem() throws Exception{
        //准备参数
        Long userAccountId = 1L;
        Long goodsSkuId = 1L;

        AddShoppingCartItemQuery query = new AddShoppingCartItemQuery();
        query.setUserAccountId(userAccountId);
        query.setGoodsSkuId(goodsSkuId);

        //模拟一下service组件的行为
        when(shoppingCartService.addShoppingCartItem(userAccountId,goodsSkuId)).thenReturn(true);

        //调用http接口请求，以及比较返回结果
        mockMvc.perform(post("/cart/item/add")
                .contentType("application/json")
                .content(JSONObject.toJSONString(query))
        )
                .andExpect(content().string("true"));
    }


}
