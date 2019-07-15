package com.ablog.api;

import com.ablog.utils.IPUtil;
import com.ablog.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UploadAPI {

    //上传真实地址
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    //映射地址
    @Value("${file.url}")
    private String file_url;
    @Value("${server.port}")
    private String port;

    @PostMapping("/api/upload/{module}")
    public String upload(@RequestParam("image") MultipartFile file, @PathVariable(name = "module")String module){

        String fileurl = "";
        //获取本机ip
        String ip = IPUtil.getIpAddress();
        //拼装地址前缀  http://localhost:8081/+staticAccessPath+/+图片名称
        String server_url = UploadUtil.getUrl(ip,this.port,this.file_url);
        try {
            fileurl = UploadUtil.uploadFile(uploadFolder,module,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server_url + module + "/" + fileurl;

    }

}
