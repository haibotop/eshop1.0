package com.gsj.www.common.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期辅助组件
 *
 * @author Holy
 * @create 2019 - 06 - 16 18:59
 */
@Component
public class DateProvider {

    /**
     * 获取当前时间
     * @return 当前时间
     * @throws Exception
     */
    public Date getDateFormatter() throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormatter.parse(dateFormatter.format(new Date()));
    }

}
