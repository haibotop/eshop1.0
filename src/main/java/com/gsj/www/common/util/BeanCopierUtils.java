package com.gsj.www.common.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanCopier工具类
 * @author Holy
 * @create 2019 - 06 - 20 22:50
 */
public class BeanCopierUtils {
    /**
     * BeanCopier缓存
     */
    public static Map<String,BeanCopier> beanCopierCacheMap = new HashMap<String, BeanCopier>();
}
