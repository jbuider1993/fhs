package com.fhs.core.base.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheUpdateManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.ListUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.autodel.service.AutoDelService;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.cache.annotation.Cacheable;
import com.fhs.core.cache.annotation.Namespace;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.service.impl.TransService;
import com.fhs.logger.Logger;
import com.mybatis.jpa.annotation.CatTableFlag;
import com.mybatis.jpa.cache.JpaTools;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 业务层base类，主要提供对数据库的CRUD操作
 *
 * @author wanglei
 * @version [版本号, 2015年5月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseServiceImpl<V extends VO, D extends BaseDO> implements BaseService<V, D>, InitializingBean {

    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * 缓存 默认时间：半个小时
     */
    @CreateCache(expire = 1800, name = "docache:", cacheType = CacheType.BOTH)
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
     * 缓存的namespace
     */
    private String nameSpace;


    /**
     * 利用spring4新特性泛型注入
     */
    @Autowired
    protected FhsBaseMapper<D> baseMapper;

    @Autowired
    private CacheUpdateManager cacheUpdateManager;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private AutoDelService autoDelService;

    private static final Set<String> exist = new HashSet<>();

    @Autowired
    private SqlSessionTemplate sqlsession;

    public BaseServiceImpl() {
        //判断自己是否需要支持缓存
        this.isCacheable = this.getClass().isAnnotationPresent(Cacheable.class);
        if (isCacheable) {
            this.namespace = this.getClass().getAnnotation(Cacheable.class).value();
        }
        else if(this.getClass().isAnnotationPresent(Namespace.class)){
            this.namespace = this.getClass().getAnnotation(Namespace.class).value();
        }
    }

    @Override
    public int addFromMap(Map<String, Object> info) {
        int result = baseMapper.addFromMap(info);
        this.refreshCache();
        return result;
    }

    @Override
    public int add(D bean) {
        int result = baseMapper.insertSelective(bean);
        this.refreshCache();
        this.addCache(bean);
        return result;
    }

    @Override
    public boolean updateFormMap(Map<String, Object> map) {
        boolean result = baseMapper.updateFormMap(map) > 0;
        this.refreshCache();
        return result;
    }

    @Override
    public boolean update(D bean) {
        boolean result = this.updateJpa(bean);
        this.refreshCache();
        this.updateCache(bean);
        return result;
    }

    @Override
    public boolean updateJpa(D bean) {
        boolean result = baseMapper.updateSelectiveById(bean) > 0;
        this.refreshCache();
        this.updateCache(bean);
        return result;
    }

    @Override
    public boolean deleteFromMap(Map<String, Object> map) {
        boolean result = baseMapper.deleteFromMap(map) > 0;
        this.refreshCache();
        return result;
    }

    @Override
    public boolean delete(D bean) {
        boolean result = baseMapper.deleteBean(bean) > 0;
        this.refreshCache();
        return result;
    }

    @Override
    public int findCountFromMap(Map<String, Object> map) {
        int result = baseMapper.findCountFromMap(map);
        return result;
    }

    @Override
    public int findCount(D bean) {
        return (int) baseMapper.selectCountJpa(bean);
    }

    @Override
    public int findCountJpa(D bean) {
        return (int) baseMapper.selectCountJpa(bean);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<V> findForList(D bean) {
        bean.setIsDelete(Constant.INT_FALSE);
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
        bean.setIsDelete(Constant.INT_FALSE);
        List<D> dos = baseMapper.selectPageJpa(bean, pageStart, pageSize);
        return dos2vos(dos);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<V> findForListFromMap(Map<String, Object> map) {
        List<D> dos = baseMapper.findForListFromMap(map);
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
        bean.setIsDelete(Constant.INT_FALSE);
        return d2v(baseMapper.selectBean(bean));
    }

    @Override
    public V findBeanById(Object id) {
        D dox = baseMapper.selectByIdJpa(id);
        return d2v(dox);
    }

    @Override
    public int updateBatch(List<Map<String, Object>> list) {
        int result = baseMapper.updateBatch(list);
        this.refreshCache();
        return result;
    }

    @Override
    public int addBatch(Map<String, Object> paramMap) {
        int result = baseMapper.addBatch(paramMap);
        this.refreshCache();
        return result;
    }


    @Override
    public int insertSelective(D entity) {
        addCache(entity);
        int result = baseMapper.insertSelective(entity);
        this.refreshCache();
        return result;
    }

    /**
     * 添加缓存
     *
     * @param entity 实体类
     */
    protected void addCache(D entity) {
        if (this.isCacheable && JpaTools.persistentMetaMap.containsKey(entity.getClass().getName())) {
            String pkey = getPkeyVal(entity);
            this.doCache.put(namespace + ":" + pkey, entity);
        }
    }

    /**
     * 刷新缓存,包括do缓存,autotrans缓存,以及其他模块依赖的缓存
     */
    protected void refreshCache() {
        cacheUpdateManager.clearCache(namespace);
        AutoTrans autoTrans = this.getClass().getAnnotation(AutoTrans.class);
        if (autoTrans != null) {
            //发送刷新的消息
            Map<String, String> message = new HashMap<>();
            message.put("transType", TransType.AUTO_TRANS);
            message.put("namespace", autoTrans.namespace());
            redisCacheService.convertAndSend("trans", JSONUtils.toJSONString(message));
        }
        if (this.nameSpace !=null ) {
            this.cacheUpdateManager.clearCache(nameSpace);
        }
    }

    /**
     * 清除缓存
     *
     * @param pkey 主键
     */
    protected void removeCache(Object pkey) {
        if (this.isCacheable) {
            this.doCache.remove(namespace + ":" + pkey);
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
        int result = baseMapper.insertSelective(entity);
        this.refreshCache();
        return result;
    }


    @Override
    public int insert(D entity) {
        return baseMapper.insertSelective(entity);
    }


    @Override
    public int batchInsert(List<D> list) {
        int result = baseMapper.batchInsert(list);
        this.refreshCache();
        return result;
    }

    @Override
    public int deleteById(Object primaryValue) {
        autoDelService.deleteCheck(this.nameSpace, primaryValue);
        D d = baseMapper.selectByIdJpa(primaryValue);
        d.setIsDelete(Constant.INT_TRUE);
        int result = baseMapper.updateByIdJpa(d);
        autoDelService.deleteItemTBL(this.nameSpace, primaryValue);
        this.refreshCache();
        removeCache(primaryValue);
        return result;
    }

    @Override
    public int updateById(D entity) {
        updateCache(entity);
        this.refreshCache();
        return baseMapper.updateByIdJpa(entity);
    }

    @Override
    public int updateSelectiveById(D entity) {
        updateCache(entity);
        this.refreshCache();
        return baseMapper.updateSelectiveById(entity);
    }

    /**
     * 缓存更新
     *
     * @param entity
     */
    protected void updateCache(D entity) {
        if (this.isCacheable) {
            String pkey = this.getPkeyVal(entity);
            this.doCache.remove(namespace + ":" + pkey);
            this.doCache.put(namespace + ":" + pkey, entity);
        }
    }

    @Override
    public int batchUpdate(List<D> entitys) {
        if (entitys == null || entitys.isEmpty()) {
            return 0;
        }
        int result = baseMapper.batchUpdateById(entitys);
        for (D entity : entitys) {
            updateCache(entity);
        }
        this.refreshCache();
        return result;
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
        entity.setIsDelete(Constant.INT_FALSE);
        return dos2vos(baseMapper.selectPageJpa(entity, pageStart, pageSize));
    }

    @Override
    public List<V> selectPageForOrder(D entity, long pageStart, long pageSize, String orderBy) {
        entity.setIsDelete(Constant.INT_FALSE);
        return dos2vos(baseMapper.selectPageForOrder(entity, pageStart, pageSize, orderBy));
    }


    @Override
    public long selectCount(D entity) {
        entity.setIsDelete(Constant.INT_FALSE);
        return baseMapper.selectCountJpa(entity);
    }

    @Override
    public List<V> select() {
        return ListUtils.copyListToList(baseMapper.select(), this.getVOClass());
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
        param.setIsDelete(Constant.INT_FALSE);
        return d2v(baseMapper.selectBean(param));
    }


    @Override
    public int deleteBean(D entity) {
        List<D> dos = baseMapper.selectPageJpa(entity, -1, -1);
        if(dos.isEmpty()){
            return 0;
        }
        for (D d : dos) {
            d.setIsDelete(Constant.INT_TRUE);
            autoDelService.deleteCheck(this.nameSpace, d.getPkey());
            autoDelService.deleteItemTBL(this.nameSpace, d.getPkey());
        }
        //批量修改为已删除
        return baseMapper.batchUpdateById(dos);
    }


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
    public List<?> callSqlIdForMany(String sqlId, Object param) {
        return sqlsession.selectList(nameSpace + "." + sqlId, param);
    }

    @Override
    public int callSqlIdForInt(String sqlId, Object param) {
        return sqlsession.selectOne(nameSpace + "." + sqlId, param);
    }


    @Override
    public int deleteBatchIds(List<?> idList) {
        if (idList == null || idList.isEmpty()) {
            return 0;
        }
        for (Object id : idList) {
            autoDelService.deleteCheck(this.nameSpace, id);
            autoDelService.deleteItemTBL(this.nameSpace, id);
        }
        List<D> dos = baseMapper.selectByIds(idList);
        if(dos.isEmpty()){
            return 0;
        }
        for (D d : dos) {
            d.setIsDelete(Constant.INT_TRUE);
        }
        return baseMapper.batchUpdateById(dos);
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
        return dos2vos(baseMapper.selectByIds(ids));
    }


    protected Class<D> doClass;

    protected Class<V> voClass;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.doClass = getTypeArgumentsClass(1);
        this.voClass = getTypeArgumentsClass(0);
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
     *
     * @param vo vo
     * @return
     */
    @Override
    public D v2d(V vo) {
        return (D) vo;
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
     *
     * @param dos
     * @return
     */
    public List<V> dos2vos(List<D> dos) {
        List<V> vos = ListUtils.copyListToList(dos, this.getVOClass());
        transService.transMore(vos);
        return vos;
    }

    /**
     * 自动删除子表数据
     *
     * @param field       字段
     * @param mainTblPkey 主表pkey
     * @return
     */
    public int deleteForMainTblPkey(String field, Object mainTblPkey) {
        try {
            D d = this.getDOClass().newInstance();
            ReflectUtils.setValue(d, field, mainTblPkey);
            return this.deleteBean(d);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据主表id和 子表字段查询子表数据
     *
     * @param field       子表字段
     * @param mainTblPkey 主表id
     * @return 多少条子表数据
     */
    public int findCountForMainTblPkey(String field, Object mainTblPkey) {
        try {
            D d = this.getDOClass().newInstance();
            ReflectUtils.setValue(d, field, mainTblPkey);
            return this.findCount(d);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
