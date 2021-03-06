package com.fhs.file.action;


import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.file.bean.ServiceFile;
import com.fhs.file.business.FileServerBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * um编辑器图片上传处理
 * by jackwong
 */
@RequestMapping("um")
@Controller
public class UMEditAction {
    @Autowired
    private FileServerBusiness fileServerBusiness;

    /**
     * 上传图片
     * @param upfile 文件
     * @return 文件对象
     */
    @ResponseBody
    @RequestMapping("imageUp")
    public String imageUp(MultipartFile upfile) {
        if (upfile == null ) {
            throw new ParamException("文件不能为空");
        }
        ServiceFile file = fileServerBusiness.uploadFileForList (Arrays.asList (upfile)).get (0);
        String result = "<script>window.name='{\"name\":\""+ file.getFileName() +"\", \"originalName\": \""+  file.getFileName() +"\", \"size\": "+
                upfile.getSize()
                +", \"state\": \"SUCCESS\", \"type\": \""+ file.getFileSuffix() +"\", \"url\": \"" + EConfig.getPathPropertiesValue("basePath")
                +"/downLoad/file?fileId=" + file.getFileId() + "\"}' </script>";
        System.out.println(result);
        return result;
    }
}
