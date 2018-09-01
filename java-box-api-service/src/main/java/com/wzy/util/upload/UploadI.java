package com.wzy.util.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadI {

    /**
     * 上传文件
     * @param file
     * @return
     */
    public FileVo uploadFile(MultipartFile file) throws Exception;

    /**
     * 上传文件文件名随机产生
     * @return
     * @throws Exception
     */
    public FileVo uploadRandomFile(MultipartFile file) throws Exception;
}
