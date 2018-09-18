package com.wzy.util.upload;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    static UploadI uploadI = new UploadImpl();

    public static FileVo uploadFile(MultipartFile file) throws Exception{
        return uploadI.uploadFile(file);
    }

    public static FileVo uploadRandomFile(MultipartFile file) throws Exception {
        return uploadI.uploadRandomFile(file);
    }
}
