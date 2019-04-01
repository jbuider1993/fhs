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
package com.fhs.system.service;

import com.fhs.system.bean.ServiceWordbookGroup;
import com.fhs.system.bean.Wordbook;

import java.util.List;
import java.util.Map;


/**
 * 字典功能
 *
 * @author  nanshouxiao
 * @version  [版本号, 2015年12月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WordbookAndGroupService
{

    /**
     * 添加字典值
     *
     * @param wordbook
     * @return
     */
    boolean addWordbook(Wordbook wordbook);

    /**
     * 修改字典值
     *
     * @param wordbook
     * @return
     */
    boolean updateWordbook(Wordbook wordbook);

    /**
     * 删除字典值
     *
     * @param wordbook
     * @return
     */
    boolean delWordbook(Wordbook wordbook);

    /**
     * 查询字典值数量
     *
     * @param wordbook
     * @return
     */
    int findWordbookCount(Wordbook wordbook);

    /**
     * 查询字典值列表
     *
     * @param map
     * @return
     */
    List<Wordbook> findWordbookForListFromMap(Map<String, Object> map);

    /**
     * 添加字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean addWordbookGroup(ServiceWordbookGroup wordbookGroup);

    /**
     * 修改字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean updateWordbookGroup(ServiceWordbookGroup wordbookGroup);

    /**
     * 删除字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean delWordbookGroup(ServiceWordbookGroup wordbookGroup);

    /**
     * 查询字典类型数量
     *
     * @param wordbookGroup
     * @return
     */
    int findWordbookGroupCount(ServiceWordbookGroup wordbookGroup);

    /**
     * 查询字典类型列表
     *
     * @param map
     * @return
     */
    List<ServiceWordbookGroup> findWordbookGroupForListFromMap(Map<String, Object> map);

    /**
     * 获取字典bean
     *
     * @param wordbook
     * @return
     */
    Wordbook getWordbookBean(Wordbook wordbook);

    /**
     * 获取字典类型bean
     *
     * @param wordbookGroup
     * @return
     */
    ServiceWordbookGroup getWordbookGroupBean(ServiceWordbookGroup wordbookGroup);

    /**
     * 刷新redis缓存
     *
     * @param wordbook
     * @return
     */
    boolean refreshRedisCache(Wordbook wordbook);

}
