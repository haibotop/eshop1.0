package com.gsj.www.inventory.async;

/**
 * 商品库存更新信息
 *
 * @author Holy
 * @create 2019 - 07 - 16 7:14
 */
public class GoodsStockUpdateMessage {
    /**
     * 商品库存更新操作
     */
    private Integer operation;
    /**
     * 核心参数数据
     */
    private Object parameter;

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
