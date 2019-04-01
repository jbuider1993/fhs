package com.fhs.ucenter.bean;

/**
 *  系统文件公共存放对象
 * @author  wanglei
 * @version  [版本号, 2015年7月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TSysFile
{
    /**
     * 文件id
     */
    private String fileId;
    
    /**
     * 文件后缀
     */
    private String fileSuffix;
    
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 物业公司编码
     */
    private String groupCode;
    
    /**
     * 给文件id赋值
     */
    public void setFileId(String fileId)
    {
        this.fileId = fileId;
    }
    
    /**
     * 获取文件id
     */
    public String getFileId()
    {
        return fileId;
    }
    
    /**
     * 给文件后缀赋值
     */
    public void setFileSuffix(String fileSuffix)
    {
        this.fileSuffix = fileSuffix;
    }
    
    /**
     * 获取文件后缀
     */
    public String getFileSuffix()
    {
        return fileSuffix;
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
     * 给物业公司编码赋值
     */
    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }
    
    /**
     * 获取物业公司编码
     */
    public String getGroupCode()
    {
        return groupCode;
    }
}
