/*
 * 文 件 名:  ServiceFileDAO.java
 * 版    权:  sxpartner Technology International Ltd.
 * 描    述:  &lt;描述&gt;.
 * 修 改 人:  wanglei
 * 修改时间:  ${date}
 * 跟踪单号:  &lt;跟踪单号&gt;
 * 修改单号:  &lt;修改单号&gt;
 * 修改内容:  &lt;修改内容&gt;
 */
package com.fhs.fileService.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.fileService.bean.ServiceFile;
import org.springframework.stereotype.Repository;

/**
 * 文件 beancrud dao
 * @author  王磊
 * @version  [版本号, 2015/08/14 11:34:23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@MapperDefinition(domainClass = ServiceFile.class)
public interface ServiceFileDAO extends BaseDao<ServiceFile>
{
}