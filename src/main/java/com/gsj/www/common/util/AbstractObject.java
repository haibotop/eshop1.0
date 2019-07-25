package com.gsj.www.common.util;

/**
 * 基础POJO类
 *
 * @author Holy
 * @create 2019 - 07 - 23 7:12
 */
public class AbstractObject {
    public <T> T clone(Class<T> clazz) throws Exception{
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this,target);
        return target;
    }
}
