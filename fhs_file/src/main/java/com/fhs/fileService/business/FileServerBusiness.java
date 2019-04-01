package com.fhs.fileService.business;

import com.fhs.core.config.EConfig;
import com.fhs.fileService.bean.ServiceFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 *
 */
public interface FileServerBusiness {
    /** filep */
    String fileP =  File.separator;



    /**
     * 上传文件
     * @param fileData
     * @return
     */
    ServiceFile uploadFile(MultipartFile fileData);

    /**
     * 上传多个文件
     * @param allFileData
     * @return
     */
    List<ServiceFile> uploadFileForList(List<MultipartFile> allFileData);
}
