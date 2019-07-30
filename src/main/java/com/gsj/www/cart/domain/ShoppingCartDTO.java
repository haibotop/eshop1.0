package com.gsj.www.cart.domain;

import com.gsj.www.common.util.AbstractObject;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 购物车DTO类
 *
 * @author Holy
 * @create 2019 - 06 - 14 6:36
 */
public class ShoppingCartDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;
    /**
     * 用户账号id
     */
    private Long userAccountId;
    /**
     * 购物车的创建时间
     */
    private Date gmtCreate;
    /**
     * 购物车的修改时间
     */
    private Date gmtModified;

    private List<ShoppingCartItemDTO> shoppingCartItemDTOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<ShoppingCartItemDTO> getShoppingCartItemDTOS() {
        return shoppingCartItemDTOS;
    }

    public void setShoppingCartItemDTOS(List<ShoppingCartItemDTO> shoppingCartItemDTOS) {
        this.shoppingCartItemDTOS = shoppingCartItemDTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartDTO)) return false;
        ShoppingCartDTO that = (ShoppingCartDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userAccountId, that.userAccountId) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccountId, gmtCreate, gmtModified);
    }
}
