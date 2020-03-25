package com.fhs.publics.service.impl;

import com.fhs.common.utils.FileUtils;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.logger.Logger;
import com.fhs.publics.dox.ServiceFileDO;
import com.fhs.publics.service.FileStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 硬盘文件存储
 */

@Service
@DataSource("file")
public class DiskFileStorage<mian> implements FileStorage {

    private static final Logger LOG = Logger.getLogger(DiskFileStorage.class);

    private static final String SEPARATOR = File.separator;

    /**
     * 获取文件对象
     *
     * @param serviceFile serviceFile
     * @param token       token(可为null)
     * @return serviceFile+token定位到的文件对象
     */
    private File getFile(ServiceFileDO serviceFile, String token) {
        String fileName = (null == token ? serviceFile.getFileId() : token) + serviceFile.getFileSuffix();
        return new File(EConfig.getPathPropertiesValue("saveFilePath") + SEPARATOR + serviceFile.getUploadDate() + SEPARATOR + serviceFile.getFileSuffix().replace(".", "") + SEPARATOR + fileName);
    }

    @Override
    public void uploadFile(ServiceFileDO serviceFile, MultipartFile fileData) {
        File file = getFile( serviceFile, null);
        try {
            FileUtils.copyInputStreamToFile(fileData.getInputStream(), file);
        } catch (IOException e) {
            LOG.error("文件上传失败", e);
        }
    }

    @Override
    public void uploadFileByToken(byte[] bytes, String token, ServiceFileDO serviceFile) {
        File file = getFile( serviceFile, token);
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(bytes);
        } catch (IOException e) {
            LOG.error("文件上传失败", e);
        }
    }

    @Override
    public void downloadFile(ServiceFileDO serviceFile, HttpServletResponse response) {
        File file = getFile( serviceFile, null);
        if (file.exists()) {
            FileUtils.download(file, response, serviceFile.getFileName());
            return;
        }
        throw new ParamException("文件不存在：" + serviceFile.getFileId());
    }

    @Override
    public void downloadFileByToken(String token, ServiceFileDO serviceFile, HttpServletResponse response) {
        File file = getFile( serviceFile, token);
        if (file.exists()) {
            FileUtils.download(file, response, file.getName());
        }
    }

    @Override
    public boolean checkFileIsExist(String token, ServiceFileDO serviceFile) {
        return getFile( serviceFile,  token).exists();
    }

    @Override
    public InputStream getFileInputStream(ServiceFileDO serviceFile) throws FileNotFoundException {
        return new FileInputStream(getFile( serviceFile,  null));
    }
}
