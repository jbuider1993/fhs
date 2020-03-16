package com.fhs.core.base.mapper;

import com.mybatis.jpa.mapper.MybatisBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 注意不要优先使用本接口自己定义的方法
 * 优先使用MybatisBaseMapper 里面的方法
 * 因为里面的方法不需要自己写xml 写sql去实现
 * @author  wanglei
 * @version  [版本号, 2015年5月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface FhsBaseMapper<D> extends MybatisBaseMapper<D> {
    /**
     * 将一个map里面的数据插入到数据库
     * @param info 数据
     * @return 默认为影响条数
     */
    int addFromMap(Map<String,Object> info);

    /**
     * 将一个obj插入到数据库
     * @param bean bean
     * @return 默认为影响条数
     */
    int add(D bean);

    /**
     * 更新数据库数据，参数为map
     * @param map map
     * @return 默认为影响条数
     */
    int updateFormMap(Map<String,Object> map);

    /**
     * 更新数据库数据，参数为object
     * @param bean object
     * @return 默认为影响条数
     */
    int update(D bean);

    /**
     * 删除数据库数据，参数为map
     * @param map map
     * @return 默认为影响条数
     */
    int deleteFromMap(Map<String,Object> map);

    /**
     * 删除数据库 数据  参数为object
     * @param bean bean
     * @return 默认为影响条数
     */
    int delete(D bean);

    /**
     * 查询 返回一行一列 结果为int类型 参数为map
     * @param map map
     * @return 结果
     */
    int findCountFromMap(Map<String,Object> map);

    /**
     * 查询 返回一行一列 结果为int类型 参数为obj
     * @param bean bean
     * @return 结果
     */
    int findCount(D bean);

    /**
     * 查询数据 参数为object
     * @param bean bean
     * @return 查询出来的数据集合
     */
    List<D> findForList(Object bean);

    /**
     * 查询数据 参数为map
     * @param map map
     * @return 查询出来的数据集合
     */
    List<D> findForListFromMap(Map<String,Object> map);

    /**
     * 查询map数据集合 -- 一般用于列表页面
     * @param map  参数
     * @return map数据集合
     */
    List<Map<String,Object>> findMapList(Map<String,Object> map);

    /**
     * 查询一条数据，返回map
     * @param map 参数为map
     * @return 一行数据
     */
    D findBeanFromMap(Map<String,Object> map);

    /**
     *查询一条数据 返回object
     * @param bean
     * @return
     */
    D findBean(D bean);

    /**
     * 批处理更新数据
     * @param list list 数据集合
     * @return 受影响行数
     */
    int updateBatch(List<Map<String,Object>> list);

    /**
     * 批处理添加数据
     * @param paramMap paramMap 参数 包含dataList  数据集合
     * @return 受影响行数
     */
    int addBatch(Map<String,Object> paramMap);
}
