package com.fhs.fileStorage.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.fhs.common.utils.FileUtils;
import com.fhs.common.utils.Logger;
import com.fhs.fileService.bean.ServiceFile;
import com.fhs.fileStorage.FileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 阿里云文件存储
 */
public class AliyunOSSFileStorage implements FileStorage {

    private static final Logger LOG = Logger.getLogger(AliyunOSSFileStorage.class);

    @Value("${fhs.file.oss.endpoint}")
    private String endpoint;

    @Value("${fhs.file.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${fhs.file.oss.accessKeySecret}")
    private String accessKeySecret;


    @Value("${fhs.file.oss.bucketname}")
    private String bucketname;


    @Override
    public void uploadFile(ServiceFile serviceFile, MultipartFile file) {
        // 上传文件
        try {
           this.upload(file.getInputStream(),serviceFile.getFileId());
        } catch (IOException e) {
            LOG.error("文件上传失败", e);
        }
    }

    /**
     * 上传文件
     * @param is  inputsteam
     * @param token token
     */
    private void upload(InputStream is, String token) {
        OSSClient ossClientbatch = this.getClient();
        // 上传文件
        try {
            ossClientbatch.putObject(bucketname, token, is);
            LOG.info("文件上传成功" + token);
        } catch (Exception e) {
            LOG.error("文件上传失败", e);
        } finally {
            // 上传成功 关闭流文件
            ossClientbatch.shutdown();
        }
    }

    @Override
    public void uploadFileByToken(byte[] bytes, String token,ServiceFile  serviceFile) {
        // 上传文件
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((bytes))) {
            upload(byteArrayInputStream, token);
        } catch (IOException e) {
            LOG.error("文件上传失败", e);
        }
    }

    @Override
    public void downloadFile(ServiceFile serviceFile, HttpServletResponse response) {
        download(serviceFile.getFileId(),  response,serviceFile.getFileName());
    }

    private void download(String token, HttpServletResponse response,String fileName)
    {
        fileName = fileName == null ? "mini.png" : fileName;
        // 初始化 阿里文件服务客户端
        OSSClient ossClientdownForId = this.getClient();
        try
        {
            // 获取文件输出流
            OSSObject ossObject = ossClientdownForId.getObject(bucketname, token);
            FileUtils.downloadInputStream(ossObject.getObjectContent(), response, fileName,ossObject.getObjectMetadata().getContentLength());
        }
        catch (Exception e)
        {
            LOG.error("file not find ,the file id :" + token,e);
        }
        finally
        {
            ossClientdownForId.shutdown();
        }
    }

    @Override
    public void downloadFileByToken(String token,ServiceFile  serviceFile, HttpServletResponse response) {
        download(token,  response,null);
    }

    @Override
    public boolean checkFileIsExist(String token, ServiceFile serviceFile) {
        // 初始化 阿里文件服务客户端
        OSSClient ossClient = this.getClient();
        token = token == null ? serviceFile.getFileId() : token;
        try
        {
            // 获取文件输出流
            boolean result = ossClient.doesObjectExist(bucketname,token);
            return result;
        }
        catch (Exception e)
        {
            LOG.error("判断文件是否存在异常:" + token,e);
        }
        finally
        {
            ossClient.shutdown();
        }
        return false;
    }

    @Override
    public InputStream getFileInputStream(ServiceFile serviceFile) throws FileNotFoundException {
        // 初始化 阿里文件服务客户端
        OSSClient ossClientdownForId = this.getClient();
        try
        {
            // 获取文件输出流
            OSSObject ossObject = ossClientdownForId.getObject(bucketname, serviceFile.getFileId());
            byte[] bytes = FileUtils.input2byte(ossObject.getObjectContent());
            return new ByteArrayInputStream(bytes);
        }
        catch (Exception e)
        {
            LOG.error("文件不存在" + serviceFile.getFileId(),e);
        }
        finally
        {
            ossClientdownForId.shutdown();
        }
        throw new  FileNotFoundException();
    }

    private OSSClient getClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

}
