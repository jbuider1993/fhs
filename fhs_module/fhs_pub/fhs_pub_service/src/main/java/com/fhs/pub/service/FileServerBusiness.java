package com.fhs.pub.service;

import com.fhs.pub.vo.PubFileVO;
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
    PubFileVO uploadFile(MultipartFile fileData);

    /**
     * 上传多个文件
     * @param allFileData
     * @return
     */
    List<PubFileVO> uploadFileForList(List<MultipartFile> allFileData);
}
