package com.gsj.www.common.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件工具类
 * @author holy
 */
public class FileUtils {
    /**
     * 根据相对路径获取一个绝对路径
     * @param relativePath 相对路径
     * @return 绝对路径
     * @throws Exception
     */
    public static String getPathByRelative(String relativePath) throws Exception{
        File appBeanDir = new File(ResourceUtils.getURL("classpath:").getPath());
        if(appBeanDir.exists()){
            appBeanDir = new File("");
        }

        File targetDir = new File(appBeanDir.getAbsolutePath(), relativePath);
        if(!targetDir.exists()){
            targetDir.mkdir();
        }

        return targetDir.getAbsolutePath();
    }

    /**
     * 上传一个文件
     * @param file 文件
     * @param uploadDirPath 上传目录的路径
     * @return
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file, String uploadDirPath) throws Exception{
        File uploadDir = new File(uploadDirPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        String filename = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(uploadDirPath + filename);

        file.transferTo(targetFile);

        return targetFile.getAbsolutePath();
    }
}
