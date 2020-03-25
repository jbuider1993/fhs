package com.fhs.publics.service;

import com.fhs.publics.dox.ServiceFileDO;
import com.fhs.publics.vo.ServiceFileVO;
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
    ServiceFileVO uploadFile(MultipartFile fileData);

    /**
     * 上传多个文件
     * @param allFileData
     * @return
     */
    List<ServiceFileVO> uploadFileForList(List<MultipartFile> allFileData);
}
