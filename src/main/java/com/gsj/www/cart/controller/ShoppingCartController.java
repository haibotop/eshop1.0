package com.gsj.www.cart.controller;

import com.gsj.www.cart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车管理模块的controller组件
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    /**
     * 购物车管理模块的service组件
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车商品条目
     * @param userAccountId 用户账号id
     * @param goodsSkuId 商品sku id
     * @return 返回结果
     */
    @RequestMapping("/add")
    public Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId){
        try {
            return shoppingCartService.addShoppingCartItem(userAccountId,goodsSkuId);
        }catch (Exception e){
            logger.error("error", e);
        }
        return false;
    }
}
