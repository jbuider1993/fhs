/*
 * 文 件 名:  ServiceFileService.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.publics.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.publics.dox.ServiceFileDO;
import com.fhs.publics.vo.ServiceFileVO;

/**
 * 文件CRUD service
 * 本服务不对外开放 对外方法参见PubFileService
 * @author  王磊
 * @version  [版本号, 2015/08/14 11:34:23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ServiceFileService extends BaseService<ServiceFileVO, ServiceFileDO>
{
    /**
     * 根据fileId查询文件信息
     * @param fileId
     * @return
     */
    ServiceFileVO selectById(String fileId);

    /**
     * 获取文件上传路径
     * @return 文件上传路径
     */
    String getFileSavePath();


    /**
     * 设置文件上传路径
     * @param path 路径
     */
    void setFileSavePath(String path);

    /**
     * 获取文件路径
     * @param serviceFile 文件对象
     * @return  文件路径
     */
    public String getAllPath(ServiceFileDO serviceFile);
}