package com.gsj.www.auth.controller;

import com.gsj.www.auth.constant.PriorityType;
import com.gsj.www.auth.domain.PriorityDTO;
import com.gsj.www.auth.domain.PriorityVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        when(dateProvider.getCurrentTime()).thenReturn(currentTime);
        when(dateProvider.formatDatetime(currentTime)).thenReturn(dateFormat.format(currentTime));
        when(dateProvider.parseDatetime(dateFormat.format(currentTime))).thenReturn(currentTime);
    }

    /**
     * 测试查询根权限
     * @throws Exception
     */
    public void testListRootPriorities() throws Exception{
        Long parentId = null;
        List<PriorityDTO> rootPriorityDTOs =
    }

    /**
     * 构造模拟的权限DO集合
     * @param parentId 父权限id
     * @return 权限DO集合
     * @throws Exception
     */
    private List<PriorityDTO> createMockPriorityDTOS(Long parentId) throws Exception{
        Long id1 = 1L;
        Long id2 = 2L;

        List<PriorityDTO> rootPriorityDTOs = new ArrayList<PriorityDTO>();
        rootPriorityDTOs.add(createMockPriorityDTO(id1,parentId));
        rootPriorityDTOs.add(createMockPriorityDTO(id2,parentId));

        return rootPriorityDTOs;
    }

    /**
     * 构造一个权限DTO对象
     * @param id 权限id
     * @param parentId 父权限id
     * @return 权限DO对象
     * @throws Exception
     */
    private PriorityDTO createMockPriorityDTO(Long id,Long parentId) throws Exception{
        Random random = new Random();
        int randomInt = random.nextInt() * 100;

        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setId(id);
        priorityDTO.setCode("TEST"+randomInt);
        priorityDTO.setGmtCreate(dateProvider.getCurrentTime());
        priorityDTO.setGmtModified(dateProvider.getCurrentTime());
        priorityDTO.setParentId(parentId);
        priorityDTO.setPriorityComment("TEST" + randomInt);
        priorityDTO.setPriorityType(PriorityType.MENU);
        priorityDTO.setUrl("http://127.0.0.1/"+randomInt);

        return priorityDTO;
    }

    /**
     * 将权限DTO对象转换成权限VO对象
     * @param priorityDTO 权限DTO对象
     * @return 权限VO对象
     * @throws Exception
     */
    private PriorityVO convertPriorityDTO2VO(PriorityDTO priorityDTO) throws Exception{
        PriorityVO priorityVO = new PriorityVO();
        priorityVO.setCode(priorityDTO.getCode());
        priorityVO.setGmtCreate(dateProvider.formatDatetime(priorityDTO.getGmtCreate()));
        priorityVO.setGmtModified(dateProvider.formatDatetime(priorityDTO.getGmtModified()));
        priorityVO.setId(priorityDTO.getId());
        priorityVO.setParentId(priorityDTO.getParentId());
        priorityVO.setPriorityComment(priorityDTO.getPriorityComment());
        priorityVO.setPriorityType(priorityDTO.getPriorityType());
        priorityVO.setUrl(priorityDTO.getUrl());
        return priorityVO;

    }

    /**
     * 将权限DTO集合转换为权限VO结合
     * @param priorityDTOS 权限DTO集合
     * @return 权限VO集合
     * @throws Exception
     */
    private List<PriorityVO> convertPriorityDTOs2VOs(List<PriorityDTO> priorityDTOS) throws Exception{
        List<PriorityVO> priorityVOS = new ArrayList<PriorityVO>(priorityDTOS.size());
        for (PriorityDTO priorityDTO : priorityDTOS) {
            priorityVOS.add(convertPriorityDTO2VO(priorityDTO));
        }
        return priorityVOS;
    }
}
