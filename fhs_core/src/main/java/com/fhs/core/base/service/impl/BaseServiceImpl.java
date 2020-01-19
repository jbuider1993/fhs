package com.fhs.core.base.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.cache.annotation.Cacheable;
import com.fhs.core.exception.ParamException;
import com.fhs.core.strategy.GenInfo;
import com.fhs.core.trans.TransService;
import com.mybatis.jpa.annotation.CatTableFlag;
import com.mybatis.jpa.cache.JpaTools;
import com.mybatis.jpa.constant.ResultMapConstants;
import com.mybatis.jpa.meta.MybatisColumnMeta;
import com.mybatis.jpa.meta.PersistentMeta;
import com.mybatis.jpa.statement.MybatisStatementAdapter;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 业务层base类，主要提供对数据库的CRUD操作，在debug模式下面不处理异常，但是在正式环境下会处理异常，并记录到log中 所有的子类都需要实现getDao的方法注入自己的dao
 *
 * @author wanglei
 * @version [版本号, 2015年5月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * 缓存 默认时间：半个小时
     */
    @CreateCache(expire = 1800, name = "docache:")
    private Cache<String, T> doCache;

    /**
     * do的namespace
     */
    private String namespace;

    /**
     * 判断自己是否需要支持自动缓存
     */
    private boolean isCacheable;

    @Autowired
    protected TransService transService;

    /**
     * 利用spring4新特性泛型注入
     */
    @Autowired
    protected BaseDao<T> baseDao;


    @Autowired
    protected NestedServiceImpl<T> nestedServiceImpl;


    private static final Set<String> exist = new HashSet<>();

    @Autowired
    private SqlSessionTemplate sqlsession;

    public BaseServiceImpl() {
        //判断自己是否需要支持缓存
        this.isCacheable = this.getClass().isAnnotationPresent(Cacheable.class);
        if(isCacheable) {
            this.namespace =this.getClass().getAnnotation(Cacheable.class).value();
        }

    }

    @Override
    public int addFromMap(Map<String, Object> info) {

        return baseDao.addFromMap(info);
    }

    @Override
    @GenInfo
    public int add(T bean) {
        return baseDao.add(bean);
    }

    @Override
    public boolean updateFormMap(Map<String, Object> map) {
        return baseDao.updateFormMap(map) > 0;
    }

    @Override
    public boolean update(T bean) {
        return baseDao.update(bean) > 0;
    }

    @Override
    public boolean updateJpa(T bean) {
        return baseDao.updateSelectiveById(bean) > 0;
    }

    @Override
    public boolean deleteFromMap(Map<String, Object> map) {
        return baseDao.deleteFromMap(map) > 0;
    }

    @Override
    public boolean delete(T bean) {
        return baseDao.delete(bean) > 0;
    }

    @Override
    public int findCountFromMap(Map<String, Object> map) {
        return baseDao.findCountFromMap(map);
    }

    @Override
    public int findCount(T bean) {
        return baseDao.findCount(bean);
    }

    @Override
    public int findCountJpa(T bean) {
        return (int) baseDao.selectCountJpa(bean);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> findForList(T bean) {
        List<T> result = baseDao.selectPageJpa(bean, -1, -1);
        transService.transMore((List<SuperBean<?>>) result);
        return result;
    }

    /**
     * 查询数据 参数为object
     *
     * @param bean bean
     * @return 查询出来的数据集合
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> findForList(T bean, int pageStart, int pageSize) {
        List<T> result = baseDao.selectPageJpa(bean, pageStart, pageSize);
        transService.transMore((List<SuperBean<?>>) result);
        return result;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> findForListFromMap(Map<String, Object> map) {
        List<T> result = baseDao.findForListFromMap(map);
        transService.transMore((List<SuperBean<?>>) result);
        return result;
    }

    @Override
    public List<Map<String, Object>> findMapList(Map<String, Object> map) {
        return baseDao.findMapList(map);
    }

    @Override
    public T findBeanFromMap(Map<String, Object> map) {
        return baseDao.findBeanFromMap(map);
    }

    @Override
    public T findBean(T bean) {
        return baseDao.findBean(bean);
    }

    @Override
    public T findBeanById(Object id) {
        T bean = baseDao.selectByIdJpa(id);
        transService.transOne((SuperBean<?>) bean);
        return bean;
    }

    @Override
    public int updateBatch(List<Map<String, Object>> list) {
        return baseDao.updateBatch(list);
    }

    @Override
    public int addBatch(Map<String, Object> paramMap) {
        return baseDao.addBatch(paramMap);
    }


    @GenInfo
    @Override
    public int insertSelective(T entity) {
        addCache(entity);
        return baseDao.insertSelective(entity);
    }

    /**
     * 添加缓存
     *
     * @param entity 实体类
     */
    private void addCache(T entity) {
        if (this.isCacheable && JpaTools.persistentMetaMap.containsKey(entity.getClass().getName())) {
            String pkey = getPkeyVal(entity);
            this.doCache.put(namespace + ":" + pkey, entity);
        }
    }

    /**
     * 获取主键
     *
     * @param entity do
     * @return 主键值
     */
    private String getPkeyVal(T entity) {
        return ConverterUtils.toString(ReflectUtils.getValue(entity, JpaTools.persistentMetaMap.get(entity.getClass().getName()).getPrimaryColumnMeta().getProperty()));
    }

    @GenInfo
    @Override
    public int insertJpa(T entity) {
        return baseDao.insertJpa(entity);
    }

    @GenInfo
    @Override
    public int insert(T entity) {
        return baseDao.insertJpa(entity);
    }

    @Override
    public int batchInsert(List<T> list) {
        return baseDao.batchInsert(list);
    }

    @Override
    public int deleteById(Object primaryValue) {
        if (this.isCacheable) {
            this.doCache.remove(namespace + ":" + ConverterUtils.toString(primaryValue));
        }
        return baseDao.deleteByIdJpa(primaryValue);
    }

    @Override
    public int updateById(T entity) {
        updateCache(entity);
        return baseDao.updateByIdJpa(entity);
    }

    @Override
    public int updateSelectiveById(T entity) {
        updateCache(entity);
        return baseDao.updateSelectiveById(entity);
    }

    private void updateCache(T entity) {
        if (this.isCacheable) {
            String pkey = this.getPkeyVal(entity);
            this.doCache.remove(namespace + ":" + pkey);
            this.doCache.put(namespace + ":" + pkey, entity);
        }
    }

    @Override
    public int batchUpdate(List<T> entity) {
        return baseDao.batchUpdate(entity);
    }

    @Override
    public T selectById(Object primaryValue) {
        if (this.isCacheable) {
            String pkey = ConverterUtils.toString(primaryValue);
            T result = this.doCache.get(namespace + ":" + pkey);
            if (result == null) {
                result = baseDao.selectByIdJpa(primaryValue);
                if (result != null) {
                    this.doCache.put(namespace + ":" + pkey, result);
                }
            }
            return result;
        }
        return baseDao.selectByIdJpa(primaryValue);
    }

    @Override
    public List<T> selectPage(T entity, long pageStart, long pageSize) {
        List<T> result = baseDao.selectPageJpa(entity, pageStart, pageSize);
        transService.transMore((List<SuperBean<?>>) result);
        return result;
    }

    @Override
    public List<T> selectPageForOrder(T entity, long pageStart, long pageSize, String orderBy) {
        List<T> result = baseDao.selectPageForOrder(entity, pageStart, pageSize, orderBy);
        transService.transMore((List<SuperBean<?>>) result);
        return result;
    }


    @Override
    public long selectCount(T entity) {
        return baseDao.selectCountJpa(entity);
    }

    @Override
    public List<T> select() {
        return baseDao.select();
    }

    @Override
    public int batchInsertCatTable(List<T> list, @CatTableFlag String flag) {
        return baseDao.batchInsertCatTable(list, flag);
    }

    @Override
    public T selectByIdCatTable(String id, @CatTableFlag String catTableFlag) {
        return baseDao.selectByIdCatTable(id, catTableFlag);
    }

    @Override
    public T selectBean(T param) {
        return baseDao.selectBean(param);
    }


    public int deleteBean(T entity) {
        return baseDao.deleteBean(entity);
    }


    @Override
    public int insertByNested(T entity) {
        int result = baseDao.insertSelective(entity);
        try {
            nestedServiceImpl.insertOnetoX(entity);
        } catch (IllegalAccessException e) {
            log.error(this, e);
        }
        return result;
    }

    @Override
    public int deleteByNested(T entity) {
        int result = baseDao.deleteBean(entity);
        try {
            nestedServiceImpl.deleteOneToX(entity);
        } catch (IllegalAccessException e) {
            log.error(this, e);
        }
        return result;
    }

    public List<T> selectNested(T entity, long pageStart, long pageSize) {
        return baseDao.selectNested(entity, pageStart, pageSize);
    }


    public List<T> selectNestedForOrder(T entity, long pageStart, long pageSize, String orderBy) {
        return baseDao.selectNestedForOrder(entity, pageStart, pageSize, orderBy);
    }

    public Where<T> getWhereBuilder(String name) {
        return new Where<T>(sqlsession, this, name);
    }

    /**
     * where条件构造器
     *
     * @Filename: BaseServiceImpl.java
     * @Description:
     * @Version: 1.0
     * @Author: jackwang
     * @Email: wanglei@sxpartner.com
     * @History:<br> 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
     */
    @SuppressWarnings("hiding")
    public class Where<T> {
        private SqlSessionTemplate sqlsession;


        /**
         * 给sql 起一个id
         */
        private String name;

        /**
         * where 的过滤条件
         */
        private StringBuilder whereParam = new StringBuilder(" <where> ");

        /**
         * 参数
         */
        private Map<String, Object> paramMap = new HashMap<>();

        /**
         * do的class name
         */
        private String modelName;

        /**
         * 是否需要拼sql
         */
        boolean isInitSql;

        /**
         * 分表信息
         */
        private String catTableInfo = "";

        /**
         * sql的namespace
         */
        private String nameSpace;

        private Where(SqlSessionTemplate sqlsession, BaseService<T> baseService, String name) {
            this.sqlsession = sqlsession;
            modelName = ((ParameterizedType) baseService.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            this.name = name;
            nameSpace = JpaTools.statementAdapterMap.get(modelName).getNameSpace();
            this.isInitSql = (!exist.contains(nameSpace + "." + name));

        }

        /**
         * =
         *
         * @param field 字段名
         * @param val   值
         * @return where对象
         */
        public Where<T> eq(String field, Object val) {
            setParam(field, val);
            if (isInitSql) {
                MybatisColumnMeta column = JpaTools.persistentMetaMap.get(modelName).getColumnMetaMap().get(field);
                initIF(field, val);
                whereParam.append(
                        " AND " + column.getColumnName() + " = #{" + field + ",jdbcType=" + column.getJdbcType() + "} ");
                whereParam.append(" </if> ");
            }
            return this;

        }

        private void initIF(String field, Object val) {
            whereParam.append(" <if test=\"");
            if (String.class == val.getClass()) {
                whereParam.append(field + "  !='' and   ");
            }
            whereParam.append(field + " !=null \"> ");
        }

        /**
         * 将参数设置到参数map中去
         *
         * @param field
         * @param val
         */
        private void setParam(String field, Object val) {
            paramMap.put(field, val);
        }

        /**
         * !=
         *
         * @param field 字段名
         * @param val   值
         * @return where对象
         */
        public Where<T> nEq(String field, Object val) {
            setParam(field, val);
            if (isInitSql) {
                MybatisColumnMeta column = JpaTools.persistentMetaMap.get(modelName).getColumnMetaMap().get(field);
                initIF(field, val);
                whereParam.append(
                        " AND " + column.getColumnName() + " != #{" + field + ",jdbcType=" + column.getJdbcType() + "} ");
                whereParam.append(" </if> ");
            }
            return this;
        }

        /**
         * like %?%
         *
         * @param field 字段名
         * @param val   值
         * @return where对象
         */
        public Where<T> like(String field, Object val) {
            setParam(field, val);
            if (isInitSql) {
                MybatisColumnMeta column = JpaTools.persistentMetaMap.get(modelName).getColumnMetaMap().get(field);
                initIF(field, val);
                whereParam.append(" AND " + column.getColumnName() + " LIKE  concat('%',#{" + field + "},'%')");
                whereParam.append(" </if> ");
            }
            return this;
        }

        /**
         * like %?
         *
         * @param field 字段名
         * @param val   值
         * @return where对象
         */
        public Where<T> lLike(String field, Object val) {
            setParam(field, val);
            if (isInitSql) {
                MybatisColumnMeta column = JpaTools.persistentMetaMap.get(modelName).getColumnMetaMap().get(field);
                initIF(field, val);
                whereParam.append(" AND " + column.getColumnName() + " LIKE  concat('%',#{" + field + "})");
                whereParam.append(" </if> ");
            }
            return this;
        }

        /**
         * like ?%
         *
         * @param field 字段名
         * @param val   值
         * @return where对象
         */
        public Where<T> rLike(String field, Object val) {
            setParam(field, val);
            if (isInitSql) {
                MybatisColumnMeta column = JpaTools.persistentMetaMap.get(modelName).getColumnMetaMap().get(field);
                initIF(field, val);
                whereParam.append(" AND " + column.getColumnName() + " LIKE  concat(#{" + field + "},'%')");
                whereParam.append(" </if> ");
            }
            return this;
        }

        /**
         * 设置分表标志
         *
         * @param field 字段名
         * @param val   值
         * @return this
         */
        public Where<T> setCatTableFlag(String field, Object val) {
            setParam(field, val);
            catTableInfo = "_${" + field + "}";
            return this;
        }


        /**
         * 查询一个
         *
         * @return
         */
        public T selectOne() {
            String sqlId = nameSpace + "." + name;
            //如果sql id不存在
            if (isInitSql) {
                initSql("select");
            }
            return sqlsession.selectOne(sqlId, paramMap);
        }

        /**
         * 查询多个
         *
         * @return
         */
        public List<T> selectList() {
            String sqlId = nameSpace + "." + name;
            //如果sql id不存在
            if (isInitSql) {
                initSql("select");
            }
            return sqlsession.selectList(sqlId, paramMap);
        }

        /**
         * 查询多个
         *
         * @return
         */
        public Integer delete() {
            String sqlId = modelName + "." + name;
            //如果sql id不存在
            if (isInitSql) {
                initSql("delete");
            }
            return sqlsession.delete(sqlId, paramMap);
        }

        /**
         * 初始化sql
         *
         * @param type sql类型
         */
        private void initSql(String type) {
            whereParam.append("</where>");
            MybatisStatementAdapter adapter = JpaTools.statementAdapterMap.get(modelName);
            // 方法名
            adapter.setMethodName(name);
            exist.add(nameSpace + "." + name);
            adapter.setParameterTypeClass(Object.class);
            PersistentMeta persistentMeta = JpaTools.persistentMetaMap.get(modelName);

            SqlCommandType sqlCommandType = null;
            String fromPreSql = null;
            switch (type) {
                case "select":
                    sqlCommandType = SqlCommandType.SELECT;
                    fromPreSql = " SELECT " + persistentMeta.getColumnNames();
                    adapter.setResultMapId(ResultMapConstants.DEFAULT_NAMESPACE + "." + persistentMeta.getEntityName());
                    // 返回值类型
                    adapter.setResultType(persistentMeta.getType());
                    break;
                case "delete":
                    sqlCommandType = SqlCommandType.DELETE;
                    adapter.setResultType(Integer.class);
                    fromPreSql = " DELETE ";
                default:
                    throw new ParamException("不支持此类型");
            }

            // sqlScript
            adapter.setSqlScript("<script>  " + fromPreSql + " FROM " + persistentMeta.getTableName() + catTableInfo + whereParam + "</script>");
            adapter.setSqlCommandType(sqlCommandType);
            // 主键策略
            adapter.setKeyGenerator(new NoKeyGenerator());
            adapter.setKeyProperty(null);
            adapter.setKeyColumn(null);
            adapter.parseStatement();
        }

    }

    private String nameSpace;

    @Override
    public Object callSqlIdForOne(String sqlId, Object param) {
        return sqlsession.selectOne(nameSpace + "." + sqlId, param);
    }

    /**
     * 初始化namespace
     */
    public void initNamespace() {
        if (nameSpace == null) {
            String modelName = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            nameSpace = JpaTools.statementAdapterMap.get(modelName).getNameSpace();
        }
    }

    @Override
    public List<Object> callSqlIdForMany(String sqlId, Object param) {
        return sqlsession.selectList(nameSpace + "." + sqlId, param);
    }

    @Override
    public int callSqlIdForInt(String sqlId, Object param) {
        return sqlsession.selectOne(nameSpace + "." + sqlId, param);
    }

    @Override
    public int deleteMP(Wrapper<T> wrapper) {
        return baseDao.delete(wrapper);
    }

    @Override
    public int deleteBatchIdsMP(Collection<? extends Serializable> idList) {
        return baseDao.deleteBatchIds(idList);
    }

    @Override
    public List<T> selectBatchIdsMP(Collection<? extends Serializable> idList) {
        return baseDao.selectBatchIds(idList);
    }

    @Override
    public T selectOneMP(Wrapper<T> queryWrapper) {
        return baseDao.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCountMP(Wrapper<T> queryWrapper) {
        return baseDao.selectCount(queryWrapper);
    }

    @Override
    public List<T> selectListMP(Wrapper<T> queryWrapper) {
        return baseDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMapsMP(Wrapper<T> queryWrapper) {
        return baseDao.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjsMP(Wrapper<T> queryWrapper) {
        return baseDao.selectObjs(queryWrapper);
    }

    @Override
    public IPage<T> selectPageMP(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPageMP(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseDao.selectMapsPage(page, queryWrapper);
    }

    @Override
    public List<T> findByIds(List<?> ids) {
        return baseDao.selectByIds(ids);
    }
}
