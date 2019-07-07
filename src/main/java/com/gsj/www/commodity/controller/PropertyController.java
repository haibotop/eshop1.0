package com.gsj.www.commodity.controller;

import com.gsj.www.commodity.domain.PropertyDTO;
import com.gsj.www.commodity.domain.PropertyQuery;
import com.gsj.www.commodity.domain.PropertyVO;
import com.gsj.www.commodity.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/commodity/property")
public class PropertyController {
    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);
    /**
     * 商品属性管理模块的service组件
     */
    @Autowired
    private PropertyService propertyService;

    /**
     * 分页查询商品属性
     * @param propertyQuery 商品属性查询条件
     * @return 商品属性
     */
    @GetMapping("/")
    public List<PropertyVO> listPropertiesByPage(PropertyQuery propertyQuery){
        try {
            List<PropertyDTO> propertyDTOS = propertyService.listPropertiesByPage(propertyQuery);
            List<PropertyVO> propertyVOS = new ArrayList<PropertyVO>(propertyDTOS.size());
            for (PropertyDTO propertyDTO : propertyDTOS) {
                propertyVOS.add(propertyDTO.clone(PropertyVO.class));
            }
            return propertyVOS;
        }catch (Exception e){
            logger.error("error",e);
        }
        return new ArrayList<PropertyVO>();
    }

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     */
    public PropertyVO getPropertyById(@PathVariable("id") Long id){
        try {
             PropertyDTO propertyDTO = propertyService.getPropertyById(id);
             return propertyDTO.clone(PropertyVO.class);
        }catch (Exception e){
            logger.error("error",e);
        }
        return new PropertyVO();
    }
}
