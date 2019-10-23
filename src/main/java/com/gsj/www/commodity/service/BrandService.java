package com.gsj.www.commodity.service;

import com.gsj.www.commodity.domain.BrandDTO;
import com.gsj.www.commodity.domain.BrandQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 品牌管理Service组件接口
 * @author holy
 */
public interface BrandService {
    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌集合
     */
    List<BrandDTO> listByPage(BrandQuery query) throws Exception;

    /**
     * 根据id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    BrandDTO getById(Long id) throws Exception;

    /**
     * 新增品牌
     * @param brandDTO 品牌
     * @param logoFile logo图片
     * @param authVoucherFile 品牌授权认证图片
     */
    void save(BrandDTO brandDTO, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception;

    /**
     * 更新品牌
     * @param brandDTO 品牌
     * @param logoFile logo图片
     * @param authVoucherFile 品牌授权认证图片
     */
    void update(BrandDTO brandDTO, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception;

    /**
     * 删除品牌
     * @param id 品牌id
     */
    void remove(Long id) throws Exception;
}
