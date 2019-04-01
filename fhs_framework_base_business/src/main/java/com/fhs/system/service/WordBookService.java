package com.fhs.system.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.system.bean.Wordbook;

import java.util.List;
import java.util.Map;


/**
 * 字典服务类
 * @author  wanglei
 * @version  [版本号, 2015年8月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WordBookService extends BaseService<Wordbook>
{
    /**
     * 获取字典分组下面的所有字典项
     * @param wordbookGroupCode 字典分组code
     * @return 字典集合
     */
    List<Wordbook> getWordBookList(String wordbookGroupCode);

    /**
     * 获取字典分组下面的所有字典项
     * @param wordbookGroupCode 字典分组code
     * @return 字典集合
     */
    Map<String,String> getWordBookMap(String wordbookGroupCode);

    /**
     * 更新缓存数据
     * @param bean 主要取wordbookgroupcode 用来过滤需要的数据
     */
    void initWordBookDataCache(Wordbook bean);

    /**
     *
     * 查询list列表
     *
     * @param bean
     * @return
     */
    List<Wordbook> findForList(Wordbook bean);

    /**
     * 将一个obj插入到数据库
     *
     * @param bean bean
     * @return 默认为影响条数
     */
    int add(Wordbook bean);

    /**
     * 更新数据库数据，参数为object
     *
     * @param bean object
     * @return 默认为影响条数
     */
    boolean update(Wordbook bean);

    /**
     * 删除数据库 数据 参数为object
     *
     * @param bean bean
     * @return 默认为影响条数
     */
    boolean delete(Wordbook bean);

    /**
     * 查询 返回一行一列 结果为int类型 参数为obj
     *
     * @param bean bean
     * @return 结果
     */
    int findCount(Wordbook bean);

    /**
     * 查询数据 参数为map
     *
     * @param map map
     * @return 查询出来的数据集合
     */
    List<Wordbook> findForListFromMap(Map<String, Object> map);

    /**
     * 查询一条数据 返回object
     *
     * @param bean
     * @return
     */
    Wordbook findBean(Wordbook bean);

    /**
     *
     * 获取字典的map
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> findMapListFromMap(Map<String, Object> map);
}
