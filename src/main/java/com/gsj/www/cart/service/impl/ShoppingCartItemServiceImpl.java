package com.gsj.www.cart.service.impl;

import com.gsj.www.cart.dao.ShoppingCartItemDAO;
import com.gsj.www.cart.domain.ShoppingCartItemDO;
import com.gsj.www.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 购物车条目管理service组件
 */
public class ShoppingCartItemServiceImpl implements com.gsj.www.cart.service.ShoppingCartItemService {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartItemServiceImpl.class);
    /**
     * 购物车条目管理DAO组件
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 删除购物车条目
     * @param id 购物车条目id
     * @return
     */
    @Override
    public Boolean remove(Long id) {
        try {
            return shoppingCartItemDAO.remove(id);
        }catch (Exception e) {
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 更新购物车条目的购买数量
     * @param id 购物车条目id
     * @param purchaseQuantity 购买数量
     * @return
     */
    @Override
    public Boolean updatePurchaseQuantity(Long id, Long purchaseQuantity) {
        try {
            ShoppingCartItemDO item = new ShoppingCartItemDO();
            item.setId(id);
            item.setPurchaseQuantity(purchaseQuantity);
            item.setGmtModified(dateProvider.getCurrentTime());
            return shoppingCartItemDAO.updateShoppingCartItem(item);
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }
}
