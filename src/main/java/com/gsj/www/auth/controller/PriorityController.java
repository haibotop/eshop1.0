package com.gsj.www.auth.controller;

import com.gsj.www.auth.domain.PriorityDTO;
import com.gsj.www.auth.domain.PriorityVO;
import com.gsj.www.auth.service.PriorityService;
import com.gsj.www.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块的controller组件
 *
 * @author Holy
 * @create 2019 - 06 - 21 7:14
 */
@RestController
@RequestMapping("/auth/priority")
public class PriorityController {
    private static final Logger logger = LoggerFactory.getLogger(PriorityController.class);

    /**
     * 权限管理模块的service组件
     */
    @Autowired
    private PriorityService priorityService;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 查询根权限
     * @return 根权限集合
     */
    @GetMapping("/root")
    public List<PriorityVO> listRootPriorities() {
        try {
            List<PriorityDTO> priorityDTOs = priorityService.listRootPriorities();
            if(priorityDTOs == null) {
                priorityDTOs = new ArrayList<PriorityDTO>();
            }

            List<PriorityVO> priorityVOs = new ArrayList<PriorityVO>(priorityDTOs.size());
            for(PriorityDTO priorityDTO : priorityDTOs) {
                priorityVOs.add(convertPriorityDTO2VO(priorityDTO));
            }

            return priorityVOs;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new ArrayList<PriorityVO>();
    }

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限id
     * @return 子权限
     */
    @GetMapping("/child/{parentId}")
    public List<PriorityVO> listChildPriorities(
            @PathVariable("parentId") Long parentId) {
        try {
            List<PriorityDTO> priorityDTOs = priorityService.listChildPriorities(parentId);
            if(priorityDTOs == null) {
                priorityDTOs = new ArrayList<PriorityDTO>();
            }

            List<PriorityVO> priorityVOs = new ArrayList<PriorityVO>(priorityDTOs.size());
            for(PriorityDTO priorityDTO : priorityDTOs) {
                priorityVOs.add(convertPriorityDTO2VO(priorityDTO));
            }

            return priorityVOs;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new ArrayList<PriorityVO>();
    }

    /**
     * 根据id查询权限
     * @param id 权限id
     * @return 权限
     */
    @GetMapping("/{id}")
    public PriorityVO getPriorityById(@PathVariable("id") Long id) {
        try {
            PriorityDTO priorityDTO = priorityService.getPriorityById(id);
            if(priorityDTO == null) {
                priorityDTO = new PriorityDTO();
            }

            return convertPriorityDTO2VO(priorityDTO);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new PriorityVO();
    }

    /**
     * 新增权限
     * @param priorityVO 权限DO对象
     */
    @PostMapping("/")
    public Boolean savePriority(@RequestBody PriorityVO priorityVO) {
        try {
            priorityService.savePriority(convertPriorityVO2DTO(priorityVO));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新权限
     * @param priorityVO 权限DO对象
     */
    @PutMapping("/{id}")
    public Boolean updatePriority(@RequestBody PriorityVO priorityVO) {
        try {
            priorityService.updatePriority(convertPriorityVO2DTO(priorityVO));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/{id}")
    public Boolean removePriority(@PathVariable("id") Long id) {
        try {
            return priorityService.removePriority(id);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 将权限DTO对象转换为VO对象
     * @param priorityDTO
     * @return
     * @throws Exception
     */
    private PriorityVO convertPriorityDTO2VO(PriorityDTO priorityDTO) throws Exception{
        PriorityVO priorityVO = priorityDTO.clone(PriorityVO.class);
        priorityVO.setGmtCreate(dateProvider.formatDatetime(priorityDTO.getGmtCreate()));
        priorityVO.setGmtModified(dateProvider.formatDatetime(priorityDTO.getGmtModified()));
        return priorityVO;
    }

    /**
     * 将权限VO对象转换为DTO对象
     * @param priorityVO
     * @return
     * @throws Exception
     */
    private PriorityDTO convertPriorityVO2DTO(PriorityVO priorityVO) throws Exception{
        PriorityDTO priorityDTO = priorityVO.clone(PriorityDTO.class);
        if(priorityVO.getGmtCreate() != null) {
            priorityDTO.setGmtCreate(dateProvider.parseDatetime(priorityVO.getGmtCreate()));
        }
        if(priorityVO.getGmtModified() != null) {
            priorityDTO.setGmtModified(dateProvider.parseDatetime(priorityVO.getGmtModified()));
        }
        return priorityDTO;
    }
}
