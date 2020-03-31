/*
 * 文 件 名:  ServiceFileServiceImpl.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.pub.service.impl;


import com.alicp.jetcache.anno.Cached;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.pub.dox.PubFileDO;
import com.fhs.pub.mapper.PubFileMapper;
import com.fhs.pub.service.PubFileService;
import com.fhs.pub.vo.PubFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 文件CRUD service
 *
 * @author 王磊
 * @version [版本号, 2015/08/14 11:34:23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@DataSource("file")
public class PubFileServiceImpl extends BaseServiceImpl<PubFileVO, PubFileDO> implements PubFileService {

    /**
     * 文件查询方法重写
     *
     * @param fileId
     * @return
     */
    @Override
    @Cached(name = "redis-file", key = "'file_fileId_' + #fileId",expire = 500)
    public PubFileVO selectById(String fileId) {
        log.infoMsg("查询文件服务:{}", fileId);
        return super.selectById(fileId);
    }

    /**
     * 文件保存路径
     */
    private String fileSavePath;

    @Override
    public String getFileSavePath() {
        return fileSavePath;
    }

    @Override
    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }

    /**
     * 获取文件下载地址
     *
     * @param serviceFile
     * @return
     */
    @Override
    public String getAllPath(PubFileDO serviceFile) {
        if(serviceFile==null)
        {
            throw new ParamException("文件不存在");
        }
        String downFilePath = EConfig.getPathPropertiesValue("downFilePath");
        String currentDate = serviceFile.getUploadDate();
        return downFilePath + currentDate
                + File.separator
                + serviceFile.getFileSuffix().replace(".", "")
                + File.separator
                + serviceFile.getFileId()
                + serviceFile.getFileSuffix();
    }

}