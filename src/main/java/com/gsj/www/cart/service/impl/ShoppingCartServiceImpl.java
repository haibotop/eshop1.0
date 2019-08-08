package com.gsj.www.cart.service.impl;

import com.gsj.www.cart.dao.ShoppingCartDAO;
import com.gsj.www.cart.dao.ShoppingCartItemDAO;
import com.gsj.www.cart.domain.ShoppingCartDO;
import com.gsj.www.cart.domain.ShoppingCartDTO;
import com.gsj.www.cart.domain.ShoppingCartItemDO;
import com.gsj.www.cart.domain.ShoppingCartItemDTO;
import com.gsj.www.cart.service.ShoppingCartService;
import com.gsj.www.commodity.domain.GoodsSkuDTO;
import com.gsj.www.commodity.service.CommodityFacadeService;
import com.gsj.www.common.util.DateProvider;
import com.gsj.www.inventory.service.InventoryService;
import com.gsj.www.promotion.domain.PromotionActivityDTO;
import com.gsj.www.promotion.service.PromotionFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 购物车管理模块的servi组件
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    /**
     * 购物车管理模块的DAO组件
     */
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * 购物车条目管理模块的DAO组件
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * 商品中心对外接口service组件
     */
    @Autowired
    private CommodityFacadeService commodityFacadeService;
    /**
     * 库存中心对外接口service组件
     */
    @Autowired
    private InventoryService inventoryService;
    /**
     * 促销中心对外接口service组件
     */
    @Autowired
    private PromotionFacadeService promotionFacadeService;

    /**
     * 添加购物车商品条目
     * @param userAccountId 用户账号id
     * @param goodsSkuId 商品sku id
     * @return 返回结果
     */
    @Override
    public Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId) {
        try {
            Date currentTime = dateProvider.getCurrentTime();
            //先查询一下用户的购物车
            ShoppingCartDO shoppingCartDO = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);

            //如果购物车不存在，则新增一个购物车
            if(shoppingCartDO == null){
                shoppingCartDO = new ShoppingCartDO();
                shoppingCartDO.setUserAccountId(userAccountId);
                shoppingCartDO.setGmtCreate(currentTime);
                shoppingCartDO.setGmtModified(currentTime);

                shoppingCartDAO.saveShoppingCart(shoppingCartDO);
            }

            //查询一下购物车中是否存在这个商品sku对应的条目
            ShoppingCartItemDO shoppingCartItemDO = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartDO.getId(), goodsSkuId);

            //如果没有这个商品条目，则新增一个商品条目
            if(shoppingCartItemDO == null){
                shoppingCartItemDO = new ShoppingCartItemDO();
                shoppingCartItemDO.setShoppingCartId(shoppingCartDO.getId());
                shoppingCartItemDO.setPurchaseQuantity(1L);
                shoppingCartItemDO.setGoodsSkuId(goodsSkuId);
                shoppingCartItemDO.setGmtCreate(currentTime);
                shoppingCartItemDO.setGmtModified(currentTime);

                shoppingCartItemDAO.saveShoppingCartItem(shoppingCartItemDO);
            }
            //如果购物车中已经存在这个商品条目了，则对已有的商品条目的购买数量累加1
            else {
                shoppingCartItemDO.setPurchaseQuantity(shoppingCartItemDO.getPurchaseQuantity() + 1L);
                shoppingCartItemDO.setGmtModified(currentTime);
                shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItemDO);
            }

        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
        return true;
    }

    /**
     * 查看用户的购物车中的数据
     * @param userAccountId 用户账号id
     * @return 购物车DTO对象
     */
    @Override
    public ShoppingCartDTO getShoppingCartDTOByUserAccountId(Long userAccountId) {
        try {
            //根据用户账号id查询一下购物车
            ShoppingCartDO shoppingCartDO = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);

            if(shoppingCartDO == null){
                return new ShoppingCartDTO();
            }

            ShoppingCartDTO shoppingCartDTO = shoppingCartDO.clone(ShoppingCartDTO.class);

            //查询购物车条目
            List<ShoppingCartItemDO> shoppingCartItemDOS = shoppingCartItemDAO.listShoppingCartItemByCartId(shoppingCartDTO.getId());

            if(shoppingCartItemDOS == null || shoppingCartItemDOS.size() ==0){
                return shoppingCartDTO;
            }

            List<ShoppingCartItemDTO> shoppingCartItemDTOS = new ArrayList<ShoppingCartItemDTO>();

            shoppingCartDTO.setShoppingCartItemDTOS(shoppingCartItemDTOS);

            //为购物车条目填充相关的数据
            for (ShoppingCartItemDO shoppingCartItemDO : shoppingCartItemDOS) {
                ShoppingCartItemDTO item = shoppingCartItemDO.clone(ShoppingCartItemDTO.class);
                setGoodsRelatedData(item);
                setStockRelatedData(item);
                setPromotionRelatedData(item);
                shoppingCartItemDTOS.add(item);
            }
            
            return shoppingCartDTO;
        }catch (Exception e){
            logger.error("error",e);
            return new ShoppingCartDTO();
        }
    }

    /**
     * 给购物车条目设置商品相关的数据
     * @param item 购物车条目
     */
    private void setGoodsRelatedData(ShoppingCartItemDTO item) throws Exception {
        GoodsSkuDTO goodsSkuDTO = commodityFacadeService.getGoodsSkuById(item.getGoodsSkuId());

        item.setGoodsId(goodsSkuDTO.getGoodsId());
        item.setGoodsHeight(goodsSkuDTO.getGoodsHeight());
        item.setGoodsLength(goodsSkuDTO.getGoodsLength());
        item.setGoodsName(goodsSkuDTO.getGoodsName());
        item.setGoodsSkuCode(goodsSkuDTO.getGoodsSkuCode());
        item.setGoodsWidth(goodsSkuDTO.getGoodsWidth());
        item.setGrossWeight(goodsSkuDTO.getGrossWeight());
        item.setSalePrice(goodsSkuDTO.getSalePrice());
    }

    /**
     * 给购物车条目设置库存相关的数据
     * @param item 购物车条目
     */
    private void setStockRelatedData(ShoppingCartItemDTO item) throws Exception{
        Long saleStockQuantity = inventoryService.getSaleStockQuantity(
                item.getGoodsSkuId());
        item.setSaleStockQuantity(saleStockQuantity);
    }

    /**
     * 给购物车条目设置促销相关的数据
     * @param item
     */
    private void setPromotionRelatedData(ShoppingCartItemDTO item) throws Exception{
        List<PromotionActivityDTO> promotionActivityDTOs = promotionFacadeService
                .listPromotionActivitiesByGoodsId(item.getGoodsId());
        item.setPromotionActivityDTOs(promotionActivityDTOs);
    }
}
