package com.fhs.core.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mybatis.jpa.annotation.CatTableFlag;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 所有的service 都必须实现此service 如果是简单的CRUD操作，
 * 允许action直接调用baseserivce实现类里面的方法。
 * 调用的时候优先使用mybatis jpa的方法，再而使用 mybatis plugs的方法最后不行再自己写sql
 * 20150729修订 调整返回类型
 *
 * @author wanglei
 * @version [版本号, 2015年5月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface BaseService<T> {

    /**
     * 将一个map里面的数据插入到数据库
     *
     * @param info 数据
     * @return 默认为影响条数
     */
    int addFromMap(Map<String, Object> info);

    /**
     * 将一个obj插入到数据库
     *
     * @param bean bean
     * @return 默认为影响条数
     */
    int add(T bean);

    /**
     * 更新数据库数据，参数为map
     *
     * @param map map
     * @return 默认为影响条数
     */
    boolean updateFormMap(Map<String, Object> map);

    /**
     * 更新数据库数据，参数为object
     *
     * @param bean object
     * @return 默认为影响条数
     */
    boolean update(T bean);

    /**
     * 更新数据库数据，参数为object
     *
     * @param bean object
     * @return 默认为影响条数
     */
    boolean updateJpa(T bean);

    /**
     * 删除数据库数据，参数为map
     *
     * @param map map
     * @return 默认为影响条数
     */
    boolean deleteFromMap(Map<String, Object> map);

    /**
     * 删除数据库 数据 参数为object
     *
     * @param bean bean
     * @return 默认为影响条数
     */
    boolean delete(T bean);

    /**
     * 查询 返回一行一列 结果为int类型 参数为map
     *
     * @param map map
     * @return 结果
     */
    int findCountFromMap(Map<String, Object> map);

    /**
     * 查询 返回一行一列 结果为int类型 参数为obj
     *
     * @param bean bean
     * @return 结果
     */
    int findCount(T bean);

    /**
     * 查询 返回一行一列 结果为int类型 参数为obj
     *
     * @param bean bean
     * @return 结果
     */
    int findCountJpa(T bean);

    /**
     * 查询数据 参数为object
     *
     * @param bean bean
     * @return 查询出来的数据集合
     */
    List<T> findForList(T bean);

    /**
     * 查询数据 参数为object
     *
     * @param bean bean
     * @return 查询出来的数据集合
     */
    List<T> findForList(T bean, int pageStart, int pageSize);

    /**
     * 查询数据 参数为map
     *
     * @param map map
     * @return 查询出来的数据集合
     */
    List<T> findForListFromMap(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    List<Map<String, Object>> findMapList(Map<String, Object> map);

    /**
     * 查询一条数据，返回map
     *
     * @param map 参数为map
     * @return 一行数据
     */
    T findBeanFromMap(Map<String, Object> map);

    /**
     * 查询一条数据 返回object
     *
     * @param bean 过滤条件
     * @return
     */
    T findBean(T bean);

    /**
     * 查询一条数据 返回object
     *
     * @param id id
     * @return
     */
    T findBeanById(Object id);

    /**
     * 批处理更新数据
     *
     * @param list list 数据集合
     * @return 受影响行数
     */
    int updateBatch(List<Map<String, Object>> list);

    /**
     * 批处理添加数据
     *
     * @param paramMap paramMap 参数 包含dataList 数据集合
     * @return 受影响行数
     */
    int addBatch(Map<String, Object> paramMap);

    /**
     * 做判空处理的insert -- jpa方法
     *
     * @param entity do
     * @return 受影响的行数
     */
    int insertSelective(T entity);

    /**
     * insert -- jpa方法
     *
     * @param entity do
     * @return 受影响的行数
     */
    int insertJpa(T entity);

    /**
     * 插入 -- jpa方法
     *
     * @param entity
     * @return int 受影响的行数
     * @since 1.0.0
     */
    int insert(T entity);

    /**
     * 批量插入 -- jpa方法
     *
     * @param list 需要插入的集合
     * @return 受影响的行数
     * @since 1.0.0
     */
    int batchInsert(List<T> list);

    /**
     * 根据id删除数据 -- jpa方法
     *
     * @param primaryValue id
     * @return 受影响行数
     * @since 1.0.0
     */
    int deleteById(Object primaryValue);

    /**
     * 根据id更新 -- jpa方法
     *
     * @param entity 待更新数据
     * @return 受影响行数
     * @since 1.0.0
     */
    int updateById(T entity);

    /**
     * 根据id跟新 -- 判空  -- jpa方法
     *
     * @param entity 待更新数据
     * @return 受影响行数
     * @since 1.0.0
     */
    int updateSelectiveById(T entity);


    /**
     * 批量更新
     *
     * @param list 需要更新的数据
     * @return 受影响条数
     */
    int batchUpdate(List<T> list);

    /**
     * 根据id、查询 -- jpa方法
     *
     * @param primaryValue id
     * @return model
     * @since 1.0.0
     */
    T selectById(Object primaryValue);

    /**
     * 根据分页参数返回结果
     * 如果不需要分页 pageStart或者pageSize传0即可
     *
     * @param entity    用来做过滤的参数
     * @param pageStart 开始number
     * @param pageSize  一页多少行数据
     * @return 符合条件的数据
     */
    List<T> selectPage(T entity, long pageStart, long pageSize);

    /**
     * 根据参数查询总数
     * 如果不需要分页 pageStart或者pageSize传0即可
     *
     * @param entity 用来做过滤的参数
     * @return 符合条件的数据条数
     */
    long selectCount(T entity);

    /**
     * select(这里用一句话描述这个方法的作用) -- jpa方法
     * (这里描述这个方法适用条件 – 可选)
     *
     * @return 查询所有
     * @since 1.0.0
     */
    List<T> select();

    /**
     * 批量插入.
     *
     * @param list 需要插入的集合
     * @param flag 分表标志
     * @return 受影响的行数
     * @since 1.0.0
     */
    int batchInsertCatTable(List<T> list, @CatTableFlag String flag);

    /**
     * 给句id获取流水表数据,分表
     *
     * @param id
     * @param catTableFlag
     * @return
     */
    T selectByIdCatTable(String id, @CatTableFlag String catTableFlag);

    /**
     * 根据参数不为空的字段作为过滤条件查询
     *
     * @param param 参数
     * @return 结果
     */
    T selectBean(T param);

    /**
     * 根据实体删除对象
     *
     * @param entity
     * @return
     */
    int deleteBean(T entity);


    /**
     * 级联插入
     *
     * @param entity
     * @return
     */
    int insertByNested(T entity);


    /**
     * 级联删除
     *
     * @param entity
     * @return
     */
    int deleteByNested(T entity);

    /**
     * 调用一个方法返回一个对象
     *
     * @param param 参数
     * @return 对象
     */
    Object callSqlIdForOne(String sqlId, Object param);

    /**
     * 调用一个方法返回一个集合
     *
     * @param param 参数
     * @return 集合
     */
    List<Object> callSqlIdForMany(String sqlId, Object param);

    /**
     * 调用一个方法返回一个int
     *
     * @param param 参数
     * @return int
     */
    int callSqlIdForInt(String sqlId, Object param);

    /**
     * 级联查询 支持one2one one2x
     *
     * @param entity    过滤条件
     * @param pageStart 分页开始
     * @param pageSize  分页行数
     * @return 级联查询结果
     */
    List<T> selectNested(T entity, long pageStart, long pageSize);

    /**
     * 级联查询 支持one2one one2x
     *
     * @param entity    过滤条件
     * @param pageStart 分页开始
     * @param pageSize  分页行数
     * @param orderBy   排序参数
     * @return 级联查询结果
     */
    List<T> selectNestedForOrder(T entity, long pageStart, long pageSize, String orderBy);

    /**
     * 根据分页参数返回结果
     * 如果不需要分页 pageStart或者pageSize传-1即可
     *
     * @param entity    用来做过滤的参数
     * @param pageStart 开始number
     * @param pageSize  一页多少行数据
     * @param orderBy   排序字段
     * @return 符合条件的数据
     */
    List<T> selectPageForOrder(T entity, long pageStart, long pageSize, String orderBy);

    /**
     * mybatis plus方法
     *
     * @param wrapper 过滤条件
     * @return 受影响行数
     */
    int deleteMP(Wrapper<T> wrapper);

    /**
     * 根据id集合删除
     *
     * @param idList id集合
     * @return 受影响行数
     */
    int deleteBatchIdsMP(Collection<? extends Serializable> idList);

    /**
     * 根据id集合查询
     *
     * @param idList id集合
     * @return 对应的结果
     */
    List<T> selectBatchIdsMP(@Param("coll") Collection<? extends Serializable> idList);

    /**
     * 查询单个
     *
     * @param queryWrapper 过滤条件
     * @return 单个对象
     */
    T selectOneMP(Wrapper<T> queryWrapper);

    /**
     * 查询count
     *
     * @param queryWrapper 过滤条件
     * @return 符合条件的数据数量
     */
    Integer selectCountMP(Wrapper<T> queryWrapper);

    /**
     * 查询list
     *
     * @param queryWrapper 过滤条件
     * @return 集合
     */
    List<T> selectListMP(Wrapper<T> queryWrapper);

    /**
     * 查询返回map集合
     *
     * @param queryWrapper 过滤条件
     * @return 集合
     */
    List<Map<String, Object>> selectMapsMP(Wrapper<T> queryWrapper);

    /**
     * 查询object
     *
     * @param queryWrapper 过滤条件
     * @return 集合
     */
    List<Object> selectObjsMP(Wrapper<T> queryWrapper);

    /**
     * 查询带分页
     *
     * @param page         分页信息
     * @param queryWrapper 过滤条件
     * @return 分页数据
     */
    IPage<T> selectPageMP(IPage<T> page, Wrapper<T> queryWrapper);

    /**
     * 查询分页-返回map
     *
     * @param page         分页信息
     * @param queryWrapper 过滤条件
     * @return 分页数据
     */
    IPage<Map<String, Object>> selectMapsPageMP(IPage<T> page, Wrapper<T> queryWrapper);

    /**
     * 根据id集合查询
     * @param ids ids
     * @return 对应的PO
     */
    List<T>  findByIds(List<? extends Object> ids);


}
