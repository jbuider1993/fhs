package com.fhs.pub.service;

import com.fhs.pub.dox.PubFileDO;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileStorage {

    /**
     * 根据ServiceFile和MultipartFile做文件上传
     * @param serviceFile
     * @param fileData
     */

    void uploadFile(PubFileDO serviceFile, MultipartFile fileData);

    /**
     * 根据文件字节数组,serviceFile和文件名做文件上传
     * @param bytes
     * @param token
     * @param serviceFile
     */

    void uploadFileByToken(byte[] bytes, String token, PubFileDO serviceFile);

    /**
     * 根据serviceFile下载文件
     * @param serviceFile
     * @param response
     */

    void downloadFile(PubFileDO serviceFile, HttpServletResponse response);

    /**
     * 根据文件名和serviceFile下载文件
     * @param token
     * @param response
     * @param  serviceFile
     */

    void downloadFileByToken(String token, PubFileDO serviceFile, HttpServletResponse response);

    /**
     * 判断一个文件是否存在
     * @param token  token
     * @param serviceFile file
     * @return true 存在 false不存在
     */
    boolean checkFileIsExist(String token, PubFileDO serviceFile);

    /**
     * 获取一个inputstreqam
     * @param serviceFile  文件
     * @return inputstream
     */
    InputStream getFileInputStream(PubFileDO serviceFile) throws FileNotFoundException;
}
