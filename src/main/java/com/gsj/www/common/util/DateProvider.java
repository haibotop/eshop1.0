package com.gsj.www.common.util;

import java.util.Date;

/**
 * 日期组件接口
 *
 * @author Holy
 * @create 2019 - 06 - 17 6:14
 */
public interface DateProvider {
    /**
     * 获取当前时间
     * @return
     * @throws Exception
     */
    Date getDateFormatter() throws Exception;
}
