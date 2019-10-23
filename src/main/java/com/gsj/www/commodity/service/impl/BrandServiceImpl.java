package com.gsj.www.commodity.service.impl;

import com.gsj.www.commodity.dao.BrandDAO;
import com.gsj.www.commodity.domain.BrandDO;
import com.gsj.www.commodity.domain.BrandDTO;
import com.gsj.www.commodity.domain.BrandQuery;
import com.gsj.www.commodity.service.BrandService;
import com.gsj.www.common.constant.PathType;
import com.gsj.www.common.util.DateProvider;
import com.gsj.www.common.util.FileUtils;
import com.gsj.www.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 品牌管理Service组件
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    /**
     * 品牌管理mapper组件
     */
    @Autowired
    private BrandDAO brandDao;
    /**
     * logo图片的路径类型
     */
    @Value("commodity.brand.image.upload.logo.path.type")
    private String logoPathType;
    /**
     * logo图片的上传路径
     */
    @Value("commodity.brand.image.upload.logo.path")
    private String logoPath;
    /**
     * 品牌授权认证图片的路径类型
     */
    @Value("commodity.brand.image.upload.authVoucher.path.type")
    private String authVoucherPathType;
    /**
     * 品牌授权认证图片的上传路径
     */
    @Value("commodity.brand.image.upload.authVoucher.path")
    private String authVoucherPath;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌集合
     */
    @Override
    public List<BrandDTO> listByPage(BrandQuery query) throws Exception {
        return ObjectUtils.convertList(brandDao.listByPage(query), BrandDTO.class);
    }

    /**
     * 根据id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    @Override
    public BrandDTO getById(Long id) throws Exception {
        return brandDao.getById(id).clone(BrandDTO.class);
    }

    /**
     * 新增品牌
     * @param brandDTO 品牌
     * @param logoFile logo图片
     * @param authVoucherFile 品牌授权认证图片
     */
    @Override
    public void save(BrandDTO brandDTO, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception {
        String logoPath = uploadLogoFile(logoFile);
        String authVoucherPath = uploadAoucherFile(authVoucherFile);

        brandDTO.setLogoPath(logoPath);
        brandDTO.setAuthVoucherPath(authVoucherPath);
        brandDTO.setGmtCreate(dateProvider.getCurrentTime());
        brandDTO.setGmtModified(dateProvider.getCurrentTime());

        brandDao.save(brandDTO.clone(BrandDO.class));
    }

    /**
     * 上传品牌授权认证图片
     * @param authVoucherFile 品牌授权认证图片
     * @return
     */
    private String uploadAoucherFile(MultipartFile authVoucherFile) throws  Exception{
        if(authVoucherFile == null){
            return null;
        }

        String uploadDirPath = null;
        if(PathType.RELETIVE.equals(authVoucherPathType)){
            uploadDirPath = FileUtils.getPathByRelative(authVoucherPath);
        }else {
            uploadDirPath = authVoucherPath;
        }

        String uploadFilePath = FileUtils.uploadFile(authVoucherFile, uploadDirPath);

        return uploadFilePath;
    }

    /**
     * 上传logp图片
     * @param logoFile logo图片
     * @return
     */
    private String uploadLogoFile(MultipartFile logoFile) throws Exception{
        if(logoFile == null){
            return null;
        }

        String uploadDirPath = null;
        if(PathType.RELETIVE.equals(logoPathType)){
            uploadDirPath = FileUtils.getPathByRelative(logoPath);
        }else {
            uploadDirPath = logoPath;
        }

        String uploadFilePath = FileUtils.uploadFile(logoFile, uploadDirPath);

        return uploadFilePath;
    }

    /**
     * 更新品牌
     * @param brandDTO 品牌
     * @param logoFile logo图片
     * @param authVoucherFile 品牌授权认证图片
     */
    @Override
    public void update(BrandDTO brandDTO, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception{
        String logoPath = uploadLogoFile(logoFile);
        String authVoucherPath = uploadAoucherFile(authVoucherFile);

        brandDTO.setLogoPath(logoPath);
        brandDTO.setAuthVoucherPath(authVoucherPath);
        brandDTO.setGmtModified(dateProvider.getCurrentTime());

        brandDao.update(brandDTO.clone(BrandDO.class));
    }

    /**
     * 删除品牌
     * @param id 品牌id
     */
    @Override
    public void remove(Long id) {
        brandDao.remove(id);
    }
}
