/*
 * 文 件 名:  ServiceFile.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.fileService.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * &lt;文件&gt;
 * 
 * @author 王磊
 * @version [版本号, 2015/08/14 11:34:23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_service_file")
public class ServiceFile implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7096338027750227520L;

    /**
     * id
     */
    @Id
    private String fileId;
    
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 后缀
     */
    private String fileSuffix;

    /**
     * 文件上传时间
     */
    private String uploadDate;

    /**
     * 时间长度
     */
    @Transient
    private Long timeLength;
    
    /**
     * 给id赋值
     */
    public void setFileId(String fileId)
    {
        this.fileId = fileId;
    }
    
    /**
     * 获取id
     */
    public String getFileId()
    {
        return fileId;
    }
    
    /**
     * 给文件名赋值
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    /**
     * 获取文件名
     */
    public String getFileName()
    {
        return fileName;
    }
    
    /**
     * 给后缀赋值
     */
    public void setFileSuffix(String fileSuffix)
    {
        this.fileSuffix = fileSuffix;
    }
    
    /**
     * 获取后缀
     */
    public String getFileSuffix()
    {
        return fileSuffix;
    }
    
    public String getUploadDate()
    {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public Long getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Long timeLength) {
        this.timeLength = timeLength;
    }
}
