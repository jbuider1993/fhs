package com.fhs.fileStorage.impl;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.DataSource;
import com.fhs.fileService.bean.ServiceFile;
import com.fhs.fileService.service.ServiceFileService;
import com.fhs.fileStorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 硬盘文件存储
 */

@Service
@DataSource("file")
public class DiskFileStorage implements FileStorage {

    @Autowired
    private ServiceFileService fileService;


    @Override
    public void uploadFile(ServiceFile serviceFile, File file) {






    }

    @Override
    public void uploadFileByToken(byte[] bytes, String token) {
        ServiceFile sf = new ServiceFile ();
        String suffix = token.substring (token.lastIndexOf ("."));
        String fileId = StringUtil.getUUID ( );
        String currentDate = DateUtils.getCurrentDateStr ("yyyy/MM/dd");
        String prefix = suffix.replace (".", "");
        File file =new File(EConfig.getPathPropertiesValue ("saveFilePath") + currentDate + fileP + prefix + fileP + fileId + suffix);
        try {
            FileOutputStream os = new FileOutputStream(file);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(ServiceFile serviceFile, HttpServletResponse response) {

    }

    @Override
    public void downloadFileByToken(String token, HttpServletResponse response) {

    }
}
