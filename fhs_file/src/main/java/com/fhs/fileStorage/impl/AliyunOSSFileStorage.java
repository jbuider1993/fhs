package com.fhs.fileStorage.impl;

import com.fhs.core.db.DataSource;
import com.fhs.fileService.bean.ServiceFile;
import com.fhs.fileStorage.FileStorage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 阿里云文件存储
 */

@Service
@DataSource("file")
public class AliyunOSSFileStorage implements FileStorage {


    @Override
    public void uploadFile(ServiceFile serviceFile, File file) {

    }

    @Override
    public void uploadFileByToken(byte[] bytes, String token) {

    }

    @Override
    public void downloadFile(ServiceFile serviceFile, HttpServletResponse response) {

    }

    @Override
    public void downloadFileByToken(String token, HttpServletResponse response) {

    }
}
