package com.gsj.www.commodity.controller;

import com.gsj.www.commodity.domain.BrandDTO;
import com.gsj.www.commodity.domain.BrandQuery;
import com.gsj.www.commodity.domain.BrandVO;
import com.gsj.www.commodity.service.BrandService;
import com.gsj.www.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 品牌管理controller组件
 * @author holy
 */
@RestController
@RequestMapping("/commodity/branch")
public class BrandController {
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
    /**
     * 品牌管理service组件
     */
    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param brandQuery 查询条件
     * @return 品牌
     */
    @GetMapping("/")
    public List<BrandVO> listByPage(BrandQuery brandQuery){
        try{
            return ObjectUtils.convertList(brandService.listByPage(brandQuery), BrandVO.class);
        }catch (Exception e){
            logger.error("error",e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BrandVO getById(@PathVariable("id") Long id){
        try {
            return brandService.getById(id).clone(BrandVO.class);
        }catch (Exception e){
            logger.error("error",e);
            return new BrandVO();
        }
    }

    /**
     * 新增品牌
     * @param brandVO 品牌
     * @param logoFile logo图片
     * @param authVoucherFile 品牌的授权认证图片
     * @return
     */
    @PostMapping("/")
    public Boolean save(@RequestBody BrandVO brandVO, MultipartFile logoFile, MultipartFile authVoucherFile){
        try {
            brandService.save(brandVO.clone(BrandDTO.class), logoFile, authVoucherFile);
            return true;
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 更新品牌
     * @param brandVO 品牌
     * @param logoFile logo图片
     * @param authVoucherFile 品牌授权认证图片
     * @return
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody BrandVO brandVO, MultipartFile logoFile, MultipartFile authVoucherFile){
        try {
            brandService.update(brandVO.clone(BrandDTO.class), logoFile, authVoucherFile);
            return true;
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 删除品牌
     * @param id 品牌id
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id")  Long id){
        try {
            brandService.remove(id);
            return true;
        }catch (Exception e){
            logger.error("error",e);
            return false;
        }
    }

    /**
     * 显示logo图片
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/logo/{id}")
    public void viewLogo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        FileInputStream fis = null;

        try {
            BrandDTO brand = brandService.getById(id);

            File file = new File(brand.getLogoPath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            response.setContentType("image/jpg");
            OutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }catch (Exception e){
            logger.error("error",e);
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){
                    logger.error("error",e);
                }
            }
        }
    }

    /**
     * 显示品牌授权认证图片
     * @param id 品牌id
     * @param request
     * @param response
     */
    @GetMapping("/{id}")
    public void viewAuthVoucher(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        FileInputStream fis = null;
        try {
            BrandDTO brand = brandService.getById(id);
            File file = new File(brand.getAuthVoucherPath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            response.setContentType("image/jpg");
            OutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }catch (Exception e){
            logger.error("error",e);
        }finally {
            if(fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    logger.error("error",e);
                }
            }
        }
    }
}
