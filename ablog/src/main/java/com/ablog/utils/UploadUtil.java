package com.ablog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadUtil {

    public static String  uploadFile(String path, String module, MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        fileName = UUID.randomUUID() + suffixName;
        String uplodePath = path + module + "/";
        File file1 = new File(uplodePath);
        if (!file1.exists()){
            file1.mkdirs();
        }
        String uppath=uplodePath+fileName;
        File targetFile = new File(uppath);
        file.transferTo(targetFile);
        return  fileName;
    }

    public static String   getUrl(String ip, String port, String staticAccessPath) {
        String url="http://"+ip+":"+port+staticAccessPath;
        return  url;
    }

}
