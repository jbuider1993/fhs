/*
 * 文 件 名:  WordbookService.java
 * 版    权:
 * 描    述:  <描述>
 * 修 改 人:  nanshouxiao
 * 修改时间:  2015年12月22日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.fhs.basics.service;

import com.fhs.basics.dox.ServiceWordbookDO;
import com.fhs.basics.dox.ServiceWordbookGroupDO;
import com.fhs.basics.vo.ServiceWordbookGroupVO;
import com.fhs.basics.vo.ServiceWordbookVO;

import java.util.List;
import java.util.Map;


/**
 * 字典功能
 *
 * @author nanshouxiao
 * @version [版本号, 2015年12月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ServiceWordbookAndGroupService {

    /**
     * 添加字典值
     *
     * @param wordbook
     * @return
     */
    boolean addWordbook(ServiceWordbookDO wordbook);

    /**
     * 修改字典值
     *
     * @param wordbook
     * @return
     */
    boolean updateWordbook(ServiceWordbookDO wordbook);

    /**
     * 删除字典值
     *
     * @param wordbook
     * @return
     */
    boolean delWordbook(ServiceWordbookDO wordbook);

    /**
     * 查询字典值数量
     *
     * @param wordbook
     * @return
     */
    int findWordbookCount(ServiceWordbookDO wordbook);

    /**
     * 查询字典值列表
     *
     * @param map
     * @return
     */
    List<ServiceWordbookVO> findWordbookForListFromMap(Map<String, Object> map);

    /**
     * 添加字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean addWordbookGroup(ServiceWordbookGroupDO wordbookGroup);

    /**
     * 修改字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean updateWordbookGroup(ServiceWordbookGroupDO wordbookGroup);

    /**
     * 删除字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean delWordbookGroup(ServiceWordbookGroupDO wordbookGroup);

    /**
     * 查询字典类型数量
     *
     * @param wordbookGroup
     * @return
     */
    int findWordbookGroupCount(ServiceWordbookGroupDO wordbookGroup);

    /**
     * 查询字典类型列表
     *
     * @param map
     * @return
     */
    List<ServiceWordbookGroupVO> findWordbookGroupForListFromMap(Map<String, Object> map);

    /**
     * 获取字典bean
     *
     * @param wordbook
     * @return
     */
    ServiceWordbookVO getWordbookBean(ServiceWordbookDO wordbook);

    /**
     * 获取字典类型bean
     *
     * @param wordbookGroup
     * @return
     */
    ServiceWordbookGroupVO getWordbookGroupBean(ServiceWordbookGroupDO wordbookGroup);

    /**
     * 刷新redis缓存
     *
     * @param wordbook
     * @return
     */
    boolean refreshRedisCache(ServiceWordbookDO wordbook);

}
