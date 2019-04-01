package com.fhs.system.service;

import com.fhs.system.bean.ServiceWordbookGroup;

import java.util.List;
import java.util.Map;

/**
 * 字典类型
 *
 * @author  nanshouxiao
 * @version  [版本号, 2015/12/22 15:13:20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ServiceWordbookGroupService
{
    /**
     * 将一个obj插入到数据库
     *
     * @param bean bean
     * @return 默认为影响条数
     */
    int add(ServiceWordbookGroup bean);

    /**
     * 更新数据库数据，参数为object
     *
     * @param bean object
     * @return 默认为影响条数
     */
    boolean update(ServiceWordbookGroup bean);

    /**
     * 删除数据库 数据 参数为object
     *
     * @param bean bean
     * @return 默认为影响条数
     */
    boolean delete(ServiceWordbookGroup bean);

    /**
     * 查询 返回一行一列 结果为int类型 参数为obj
     *
     * @param bean bean
     * @return 结果
     */
    int findCount(ServiceWordbookGroup bean);

    /**
     * 查询数据 参数为map
     *
     * @param map map
     * @return 查询出来的数据集合
     */
    List<ServiceWordbookGroup> findForListFromMap(Map<String, Object> map);

    /**
     * 查询一条数据 返回object
     *
     * @param bean
     * @return
     */
    ServiceWordbookGroup findBean(ServiceWordbookGroup bean);
}