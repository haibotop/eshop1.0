package com.gsj.www.auth.controller;

import com.gsj.www.auth.service.PriorityService;
import com.gsj.www.common.util.DateProvider;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.Date;

import static org.mockito.Mockito.when;

/**
 * 权限管理模块的controller组件的单元测试类
 *
 * @author Holy
 * @create 2019 - 06 - 28 6:56
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PriorityController.class)
public class PriorityControllerTest {
    /**
     * mvc测试模拟类
     */
    @Autowired
    private MockMvc mvc;
    /**
     * 权限管理模块的service组件
     */
    @MockBean
    private PriorityService priorityService;
    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;

    /**
     * 初始化方法
     * @throws Exception
     */
    @Before
    public void setup() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
        when(dateProvider.getDateFormatter()).thenReturn(currentTime);
        when(dateFormat.)
    }
}
