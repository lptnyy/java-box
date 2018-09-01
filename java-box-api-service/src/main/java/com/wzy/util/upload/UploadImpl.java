package com.wzy.util.upload;

import com.wzy.util.Md5Util;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;

public class UploadImpl implements UploadI{

    @Override
    public FileVo uploadFile(MultipartFile file) throws Exception{
        FileVo fileVo = new FileVo();
        fileVo.setFileName(file.getOriginalFilename());
        fileVo.setFileUrl("/upload/"+file.getOriginalFilename());
        String fileUrl = System.getProperty("user.dir")+"/upload";
        File saveFiles = new File(fileUrl);
        if (!saveFiles.exists()) {
            saveFiles.mkdirs();
        }
        fileUrl += "/"+fileVo.getFileName();
        File files = new File(fileUrl);
        if (files.exists()) {
            files.delete();
        }
        byte[] fileData = file.getBytes();
        fileVo.setFileLength(fileData.length);
        fileVo.setFileMd5(Md5Util.getMd5(fileData));
        FileOutputStream fileOut = new FileOutputStream(files);
        fileOut.write(fileData);
        fileOut.close();
        file.getInputStream().close();
        fileData = null;
        return fileVo;
    }

    @Override
    public FileVo uploadRandomFile(MultipartFile file) throws Exception {
        FileVo fileVo = new FileVo();
        fileVo.setFileName(file.getOriginalFilename());
        Random rand = new Random();
        String newFile = new Date().getTime()+""+rand.nextInt(99999)+".jar";
        fileVo.setFileUrl("/upload/"+newFile);
        String fileUrl = System.getProperty("user.dir")+"/upload";
        File saveFiles = new File(fileUrl);
        if (!saveFiles.exists()) {
            saveFiles.mkdirs();
        }
        fileUrl += "/"+newFile;
        File files = new File(fileUrl);
        if (files.exists()) {
            files.delete();
        }
        byte[] fileData = file.getBytes();
        fileVo.setFileLength(fileData.length);
        fileVo.setFileMd5(Md5Util.getMd5(fileData));
        FileOutputStream fileOut = new FileOutputStream(files);
        fileOut.write(fileData);
        fileOut.close();
        file.getInputStream().close();
        fileData = null;
        return fileVo;
    }
}
