package com.fhs.core.base.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.ListUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.core.base.pojo.SuperBean;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.cache.annotation.Cacheable;
import com.fhs.core.exception.ParamException;
import com.fhs.core.trans.service.impl.TransService;
import com.fhs.logger.Logger;
import com.mybatis.jpa.annotation.CatTableFlag;
import com.mybatis.jpa.cache.JpaTools;
import com.mybatis.jpa.constant.ResultMapConstants;
import com.mybatis.jpa.meta.MybatisColumnMeta;
import com.mybatis.jpa.meta.PersistentMeta;
import com.mybatis.jpa.statement.MybatisStatementAdapter;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
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
public abstract class BaseServiceImpl<V extends VO,D extends BaseDO> implements BaseService<V,D>, InitializingBean {

    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * 缓存 默认时间：半个小时
     */
    @CreateCache(expire = 1800, name = "docache:")
    private Cache<String, D> doCache;

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
    protected FhsBaseMapper<D> baseMapper;



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

        return baseMapper.addFromMap(info);
    }

    @Override
    public int add(D bean) {
        return baseMapper.add(bean);
    }

    @Override
    public boolean updateFormMap(Map<String, Object> map) {
        return baseMapper.updateFormMap(map) > 0;
    }

    @Override
    public boolean update(D bean) {
        return baseMapper.update(bean) > 0;
    }

    @Override
    public boolean updateJpa(D bean) {
        return baseMapper.updateSelectiveById(bean) > 0;
    }

    @Override
    public boolean deleteFromMap(Map<String, Object> map) {
        return baseMapper.deleteFromMap(map) > 0;
    }

    @Override
    public boolean delete(D bean) {
        return baseMapper.delete(bean) > 0;
    }

    @Override
    public int findCountFromMap(Map<String, Object> map) {
        return baseMapper.findCountFromMap(map);
    }

    @Override
    public int findCount(D bean) {
        return baseMapper.findCount(bean);
    }

    @Override
    public int findCountJpa(D bean) {
        return (int) baseMapper.selectCountJpa(bean);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<V> findForList(D bean) {
        List<D> dos = baseMapper.selectPageJpa(bean, -1, -1);
        return dos2vos(dos);
    }

    /**
     * 查询数据 参数为object
     *
     * @param bean bean
     * @return 查询出来的数据集合
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public List<V> findForList(D bean, int pageStart, int pageSize) {
        List<D> dos = baseMapper.selectPageJpa(bean, pageStart, pageSize);
        return dos2vos(dos);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<V> findForListFromMap(Map<String, Object> map) {
        List<D> dos =  baseMapper.findForListFromMap(map);
        return dos2vos(dos);
    }

    @Override
    public List<Map<String, Object>> findMapList(Map<String, Object> map) {
        return baseMapper.findMapList(map);
    }

    @Override
    public V findBeanFromMap(Map<String, Object> map) {
        return d2v(baseMapper.findBeanFromMap(map));
    }

    @Override
    public V findBean(D bean) {
        return d2v(baseMapper.findBean(bean));
    }

    @Override
    public V findBeanById(Object id) {
        D dox = baseMapper.selectByIdJpa(id);
        return d2v(dox);
    }

    @Override
    public int updateBatch(List<Map<String, Object>> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int addBatch(Map<String, Object> paramMap) {
        return baseMapper.addBatch(paramMap);
    }


    @Override
    public int insertSelective(D entity) {
        addCache(entity);
        return baseMapper.insertSelective(entity);
    }

    /**
     * 添加缓存
     *
     * @param entity 实体类
     */
    private void addCache(D entity) {
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
    private String getPkeyVal(D entity) {
        return ConverterUtils.toString(ReflectUtils.getValue(entity, JpaTools.persistentMetaMap.get(entity.getClass().getName()).getPrimaryColumnMeta().getProperty()));
    }

    @Override
    public int insertJpa(D entity) {
        return baseMapper.insertJpa(entity);
    }


    @Override
    public int insert(D entity) {
        return baseMapper.insertJpa(entity);
    }


    @Override
    public int batchInsert(List<D> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public int deleteById(Object primaryValue) {
        if (this.isCacheable) {
            this.doCache.remove(namespace + ":" + ConverterUtils.toString(primaryValue));
        }
        return baseMapper.deleteByIdJpa(primaryValue);
    }

    @Override
    public int updateById(D entity) {
        updateCache(entity);
        return baseMapper.updateByIdJpa(entity);
    }

    @Override
    public int updateSelectiveById(D entity) {
        updateCache(entity);
        return baseMapper.updateSelectiveById(entity);
    }

    private void updateCache(D entity) {
        if (this.isCacheable) {
            String pkey = this.getPkeyVal(entity);
            this.doCache.remove(namespace + ":" + pkey);
            this.doCache.put(namespace + ":" + pkey, entity);
        }
    }

    @Override
    public int batchUpdate(List<D> entity) {
        return baseMapper.batchUpdate(entity);
    }

    @Override
    public V selectById(Object primaryValue) {
        if (this.isCacheable) {
            String pkey = ConverterUtils.toString(primaryValue);
            D result = this.doCache.get(namespace + ":" + pkey);
            if (result == null) {
                result = baseMapper.selectByIdJpa(primaryValue);
                if (result != null) {
                    this.doCache.put(namespace + ":" + pkey, result);
                }
            }
            return d2v(result);
        }
        return d2v(baseMapper.selectByIdJpa(primaryValue));
    }

    @Override
    public List<V> selectPage(D entity, long pageStart, long pageSize) {
        return dos2vos(baseMapper.selectPageJpa(entity, pageStart, pageSize));
    }

    @Override
    public List<V> selectPageForOrder(D entity, long pageStart, long pageSize, String orderBy) {
        return dos2vos(baseMapper.selectPageForOrder(entity, pageStart, pageSize, orderBy));
    }


    @Override
    public long selectCount(D entity) {
        return baseMapper.selectCountJpa(entity);
    }

    @Override
    public List<V> select() {
        return ListUtils.copyListToList(baseMapper.select(),this.getVOClass());
    }

    @Override
    public int batchInsertCatTable(List<D> list, @CatTableFlag String flag) {
        return baseMapper.batchInsertCatTable(list, flag);
    }

    @Override
    public V selectByIdCatTable(String id, @CatTableFlag String catTableFlag) {
        return d2v(baseMapper.selectByIdCatTable(id, catTableFlag));
    }

    @Override
    public V selectBean(D param) {
        return d2v(baseMapper.selectBean(param));
    }


    @Override
    public int deleteBean(D entity) {
        return baseMapper.deleteBean(entity);
    }


    public List<V> selectNested(D entity, long pageStart, long pageSize) {
        return dos2vos(baseMapper.selectNested(entity, pageStart, pageSize));
    }


    public List<V> selectNestedForOrder(D entity, long pageStart, long pageSize, String orderBy) {
        return dos2vos(baseMapper.selectNestedForOrder(entity, pageStart, pageSize, orderBy));
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
    public int deleteMP(Wrapper<D> wrapper) {
        return baseMapper.delete(wrapper);
    }

    @Override
    public int deleteBatchIdsMP(Collection<? extends Serializable> idList) {
        return baseMapper.deleteBatchIds(idList);
    }

    @Override
    public List<V> selectBatchIdsMP(Collection<? extends Serializable> idList) {
        return dos2vos(baseMapper.selectBatchIds(idList));
    }

    @Override
    public V selectOneMP(Wrapper<D> queryWrapper) {
        return d2v(baseMapper.selectOne(queryWrapper));
    }

    @Override
    public Integer selectCountMP(Wrapper<D> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<V> selectListMP(Wrapper<D> queryWrapper) {
        return dos2vos(baseMapper.selectList(queryWrapper));
    }

    @Override
    public List<Map<String, Object>> selectMapsMP(Wrapper<D> queryWrapper) {
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjsMP(Wrapper<D> queryWrapper) {
        return baseMapper.selectObjs(queryWrapper);
    }

    @Override
    public IPage<D> selectPageMP(IPage<D> page, Wrapper<D> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPageMP(IPage<D> page, Wrapper<D> queryWrapper) {
        return baseMapper.selectMapsPage(page, queryWrapper);
    }

    @Override
    public List<V> findByIds(List<?> ids) {
        return ListUtils.copyListToList(baseMapper.selectByIds(ids),this.getVOClass());
    }


    protected Class<D> doClass;

    protected Class<V> voClass;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.doClass = getTypeArgumentsClass(0);
        this.voClass = getTypeArgumentsClass(1);
        init();
    }

    /**
     * 初始化
     */
    public void init() {
    }

    /**
     * 获取泛型class
     *
     * @param index 第几个
     * @return 泛型
     */
    private <T> Class<T> getTypeArgumentsClass(int index) {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[index];
        return tClass;
    }

    /**
     * vo转DO
     * @param vo vo
     * @return
     */
    @Override
    public D v2d(V vo){
        return (D)vo;
    }

    @Override
    public Class<D> getDOClass() {
        return this.doClass;
    }

    @Override
    public Class<V> getVOClass() {
        return this.voClass;
    }

    /**
     * po转vo
     *
     * @param d d
     * @return vo
     */
    public V d2v(D d) {
        try {
            if (d == null) {
                return null;
            }
            V vo = voClass.newInstance();
            BeanUtils.copyProperties(d, vo);
            transService.transOne(vo);
            return vo;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * do集合转vo集合
     * @param dos
     * @return
     */
    public List<V> dos2vos(List<D> dos){
        List<V> vos = ListUtils.copyListToList(dos,this.getVOClass());
        transService.transMore(vos);
        return vos;
    }
}
