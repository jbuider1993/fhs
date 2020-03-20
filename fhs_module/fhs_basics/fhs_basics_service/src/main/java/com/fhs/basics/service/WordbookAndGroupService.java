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

import com.fhs.basics.dox.WordbookDO;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.basics.vo.WordbookGroupVO;
import com.fhs.basics.vo.WordbookVO;

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
    boolean addWordbook(WordbookDO wordbook);

    /**
     * 修改字典值
     *
     * @param wordbook
     * @return
     */
    boolean updateWordbook(WordbookDO wordbook);

    /**
     * 删除字典值
     *
     * @param wordbook
     * @return
     */
    boolean delWordbook(WordbookDO wordbook);

    /**
     * 查询字典值数量
     *
     * @param wordbook
     * @return
     */
    int findWordbookCount(WordbookDO wordbook);

    /**
     * 查询字典值列表
     *
     * @param map
     * @return
     */
    List<WordbookVO> findWordbookForListFromMap(Map<String, Object> map);

    /**
     * 添加字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean addWordbookGroup(WordbookGroupDO wordbookGroup);

    /**
     * 修改字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean updateWordbookGroup(WordbookGroupDO wordbookGroup);

    /**
     * 删除字典类型
     *
     * @param wordbookGroup
     * @return
     */
    boolean delWordbookGroup(WordbookGroupDO wordbookGroup);

    /**
     * 查询字典类型数量
     *
     * @param wordbookGroup
     * @return
     */
    int findWordbookGroupCount(WordbookGroupDO wordbookGroup);

    /**
     * 查询字典类型列表
     *
     * @param map
     * @return
     */
    List<WordbookGroupVO> findWordbookGroupForListFromMap(Map<String, Object> map);

    /**
     * 获取字典bean
     *
     * @param wordbook
     * @return
     */
    WordbookVO getWordbookBean(WordbookDO wordbook);

    /**
     * 获取字典类型bean
     *
     * @param wordbookGroup
     * @return
     */
    WordbookGroupVO getWordbookGroupBean(WordbookGroupDO wordbookGroup);

    /**
     * 刷新redis缓存
     *
     * @param wordbook
     * @return
     */
    boolean refreshRedisCache(WordbookDO wordbook);

}
