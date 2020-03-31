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
package com.fhs.pub.mapper;

import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.pub.dox.PubFileDO;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 文件 beancrud dao
 * @author  王磊
 * @version  [版本号, 2015/08/14 11:34:23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository
@MapperDefinition(domainClass = PubFileDO.class)
public interface PubFileMapper extends FhsBaseMapper<PubFileDO>
{
}