package com.gsj.www.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象工具类
 *
 * @author Holy
 * @create 2019 - 07 - 23 7:05
 */
public class ObjectUtils {
    public static <T> List<T> convertList(List<? extends AbstractObject> sourceList, Class<T> targetClazz) throws Exception{
        List<T> targetList = new ArrayList<T>();
        for (AbstractObject sourceObject : sourceList) {
            targetList.add(sourceObject.clone(targetClazz));
        }
        return targetList;
    }
}
