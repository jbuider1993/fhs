package com.fhs.pagex.service;

import com.mybatis.jpa.cache.JpaTools;
import com.fhs.common.utils.*;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.exception.BusinessException;
import com.fhs.core.trans.TransService;
import com.fhs.pagex.bean.DefaultPageXBean;
import org.apache.commons.collections.map.HashedMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * pagex dbservice
 * 1 根据配置文件拼接sql
 * 2 根据namespace 执行sql返回结果
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.service
 * @ClassName: PageXDBService
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 20:01
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 20:01
 * @Version: 1.0
 */
@Service
public class PageXDBService {



    private static final Logger LOG = Logger.getLogger(PageXDBService.class);

    @Autowired
    private SqlSessionTemplate sqlsession;



    @Autowired
    private TransService transService;



    /**
     *  需要翻译的namespace集合
     */
    private Set<String> needTransNamespaceSet = new HashSet<>();

    /**
     * key namespace value 对应namespace的PO class
     */
    private Map<String,Class> namespaceClassMap = new HashedMap();



    /**
     * 插入一条数据到db
     * @param param 参数
     * @param namespace namespace
     * @return
     */
    public int insert(Map<String,Object> param, String namespace)
    {
        return sqlsession.insert(getSqlNamespace() + namespace + "_insertPageX",param);
    }

    /**
     * 获取数据带分页
     * @param param 参数
     * @param namespace namespace
     * @return json
     */
    public String findListPage(Map<String,Object> param, String namespace)
    {
        List<Map<String,Object>> rows = sqlsession.selectList(getSqlNamespace() + namespace + "_findPageX",param);
        //如果不包含的话就直接转换为json
        if(!needTransNamespaceSet.contains(namespace))
        {
            return  JsonUtils.list2json(rows);
        }
        final Class clazz = namespaceClassMap.get(namespace);
        final List<SuperBean<?>> superBeans = new ArrayList<>();
        // List map 转List SuperBean
        rows.forEach(row->{
            try {
                Object tempObj = clazz.newInstance();
                SuperBean<?> tempSuperBenn = (SuperBean)tempObj;
                for(String key : row.keySet())
                {
                    ReflectUtils.setValue(tempObj,key, ConverterUtils.toString(row.get(key)));
                }
                superBeans.add(tempSuperBenn);
            } catch (InstantiationException e) {
                LOG.error(this,e);
                throw new BusinessException("findListPager反射错误");
            } catch (IllegalAccessException e) {
                LOG.error(this,e);
                throw new BusinessException("findListPager反射错误参数错误");
            }
        });
        transService.transMore(superBeans);
        return JsonUtils.list2json(superBeans);
    }

    /**
     * 查询总数
     * @param param 参数
     * @param namespace namespace
     * @return 总数
     */
    public Integer findPageCount(Map<String,Object> param, String namespace)
    {
        Map<String,Object> countRow = sqlsession.selectOne(getSqlNamespace() + namespace +"_findPageCountPageX",param);
        return ConverterUtils.toInt(countRow.get("countNum"));
    }

    /**
     * 获取bena的json
     * @param param 过滤参数
     * @param namespace namespace
     * @return bean的json
     */
    public String findBean(Map<String,Object> param, String namespace){
        return JsonUtils.bean2json(sqlsession.selectOne(getSqlNamespace() + namespace + "_findBeanPageX",param));
    }

    /**
     * 获取bena的json
     * @param param 过滤参数
     * @param namespace namespace
     * @return 更新了几行默认是1
     */
    public int update(Map<String,Object> param, String namespace){
        return sqlsession.update(getSqlNamespace() + namespace + "_updatePageX",param);
    }

    /**
     * 获取bena的json
     * @param pkey 主键
     * @param namespace namespace
     * @return 删除了几行 理论来说是1
     */
    public int del(String pkey, String namespace)
    {
        return sqlsession.delete(getSqlNamespace() + namespace + "_delPageX",pkey);
    }

    /**
     * 获取mybatis 命名空间
     * @return
     */
    private String getSqlNamespace(){
        String sqlNameSpace = JpaTools.statementAdapterMap.get(DefaultPageXBean.class.getName()).getNameSpace();
        return sqlNameSpace + ".";
    }



    public Map<String,Class> getNamespaceClassMap()
    {
            return this.namespaceClassMap;
    }

    public Set<String> getNeedTransNamespaceSet() {
        return needTransNamespaceSet;
    }
}
