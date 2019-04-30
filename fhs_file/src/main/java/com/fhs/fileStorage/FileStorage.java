package com.fhs.fileStorage;

import com.fhs.fileService.bean.ServiceFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface FileStorage {

    String fileP =  File.separator;

    void uploadFile(ServiceFile serviceFile, MultipartFile file);


    void uploadFileByToken(byte[] bytes,String token);


    void downloadFile(ServiceFile serviceFile, HttpServletResponse response);


    void downloadFileByToken(String token,HttpServletResponse response);
}
