package com.fhs.pagex.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.pagex.annotation.JoinShowColumn;
import com.fhs.pagex.dao.DefaultPageXDAO;
import com.fhs.pagex.dto.PagexListSettDTO;
import com.mybatis.jpa.common.ColumnNameUtil;
import com.mybatis.jpa.common.PersistentUtil;
import com.mybatis.jpa.common.scanner.AnnotationTypeFilterBuilder;
import com.mybatis.jpa.common.scanner.SpringClassScanner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 表关联查询服务
 */
@Service
public class JoinService implements InitializingBean {

    private String basePackage = "com.fhs.**.bean";

    /**
     * pageXDBService
     */
    @Autowired
    private DefaultPageXDAO pageXDAO;

    /**
     * key model name value model对应的class
     */
    private Map<String, Class> modelClassMap = new HashMap<>();

    /**
     * 每个model对应哪些字段是可能需要被关联的  val->map key db字段名，val Java字段名
     */
    private Map<String, Map<String,String>> modelJoinFiledMap = new HashMap<>();

    /**
     * key model val 查询的sql
     */
    private Map<String, String> modelSqlMap = new HashMap<>();

    /**
     * key namespace value 此namespace需要关联查询的字段集合
     *   valMap->     key 字段名字 val namespace
     */
    private Map<String,Map<String,String>> namespaceJoinColumnMap = new HashMap<>();


    /**
     * 填充Join数据
     * @param rows 待被填充的数据集合
     * @param namespace namespace
     * @return 初始好的数据
     */
    public  JSONArray initJoinData(JSONArray rows, String namespace)
    {
        if(namespaceJoinColumnMap.containsKey(namespace)){
            Map<String,String> namespaceJoinColumnInfo = namespaceJoinColumnMap.get(namespace);
            Set<String> columnSet = namespaceJoinColumnInfo.keySet();
            for(int i = 0;i<rows.size();i++)
            {
                if(!rows.getJSONObject(i).containsKey("transMap"))
                {
                    rows.getJSONObject(i).put("transMap",new JSONObject());
                };
            }
            String tempNameSpace = null;
            String camaelColumn = null;
            //遍历所有需要join的字段
            for(String column:columnSet)
            {
                Set<String> columnVal = new HashSet<>();
                camaelColumn = ColumnNameUtil.underlineToCamel(column);
                for(int i = 0;i<rows.size();i++)
                {
                    columnVal.add(ConverterUtils.toString(rows.getJSONObject(i).get(camaelColumn)));
                }
                tempNameSpace = namespaceJoinColumnInfo.get(column);
                // 查询数据的sql
                String sql = modelSqlMap.get(tempNameSpace);
                sql = sql.replace("@{pkeys}", StringUtil.getStrToIn(columnVal));
                //一个namespace 查询出来的data集合，后面的代码将会把这个数据填充到dataList中
                List<Map<String,Object>> namespaceDataList = pageXDAO.selectListForJoin(sql);
                //关联表的一列数据
                Map<String,Map<String,Object>> joinTBLData = new HashMap<>();
                Map<String,String> joinField = this.modelJoinFiledMap.get(tempNameSpace);
                // 转换字段名字
                for(Map<String,Object> row:namespaceDataList)
                {
                    for(String dbColumnName : joinField.keySet())
                    {
                        row.put(joinField.get(dbColumnName),row.get(dbColumnName));
                        row.remove(dbColumnName);
                    }
                    String rowPkey= ConverterUtils.toString(row.get("pkey"));
                    row.remove("pkey");
                    joinTBLData.put(rowPkey,row);
                }
                for(int i = 0;i<rows.size();i++)
                {
                    JSONObject row = rows.getJSONObject(i);
                    JSONObject transMap = row.getJSONObject("transMap");
                    String pkey = ConverterUtils.toString(row.get(camaelColumn));
                    if(joinTBLData.get(pkey)!=null)
                    {
                        transMap.putAll(joinTBLData.get(pkey));
                    }
                }
            }
        }
        return rows;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        Set<Class<?>> entitySet = scanClass();
        entitySet.forEach(clazz -> {
            modelClassMap.put(clazz.getSimpleName(), clazz);
            modelJoinFiledMap.put(clazz.getSimpleName(), parseNeedJoinField(clazz));
        });
        PagexDataService.SIGNEL.registerJsRefreshListener(this::refreshJs);
    }



    /**
     * 解析此类哪些字段需要join show
     *
     * @param clazz class
     * @return 需要join show的字段 map
     */
    private Map<String,String> parseNeedJoinField(Class<?> clazz) {
        Map<String,String> needJoinFieldMap = new HashMap<>();
        List<Field> fieldList = ReflectUtils.getAllField(clazz);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ");
        String tblColumnName = null;
        for (Field field : fieldList) {
            if (!field.isAnnotationPresent(JoinShowColumn.class)) {
                continue;
            }
            tblColumnName = PersistentUtil.getColumnName(field);
            needJoinFieldMap.put(tblColumnName,clazz.getSimpleName() + "_" + field.getName());
            sqlBuilder.append(tblColumnName + ",");
        }
        String pkey = PersistentUtil.getTableName(clazz);
        sqlBuilder.append(pkey + " as pkey  FROM " + PersistentUtil.getTableName(clazz));
        sqlBuilder.append(" WHERE " + PersistentUtil.getPrimaryKey(clazz) + " IN (@{pkeys})");
        modelSqlMap.put(clazz.getSimpleName(), sqlBuilder.toString());
        return needJoinFieldMap;
    }


    /**
     * 扫描class
     *
     * @return 符合条件的class集合
     */
    private Set<Class<?>> scanClass() {
        /** scan entity **/
        TypeFilter entityFilter = AnnotationTypeFilterBuilder.build(Entity.class);
        SpringClassScanner entityScanner = new SpringClassScanner.Builder().typeFilter(entityFilter).build();
        String[] pkgs = basePackage.split(";");
        for (String pkg : pkgs) {
            entityScanner.getScanPackages().add(pkg);
        }
        Set<Class<?>> entitySet = null;
        try {
            entitySet = entityScanner.scan();

        } catch (ClassNotFoundException | IOException e) {
            // log or throw runTimeExp
            throw new RuntimeException(e);
        }
        return entitySet;
    }

    /**
     * 当有js刷新的事件 刷新 对应的sql 和 列表字段
     * @param namespace namespace
     * @param js js
     */
    public void refreshJs(String namespace,String js)
    {
        PagexListSettDTO pagexListSett = PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace);
        String joinColumns = ConverterUtils.toString(pagexListSett.getModelConfig().get("joinColumns"));
        if(CheckUtils.isNotEmpty(joinColumns))
        {
            StringBuilder joinDBColumns = new StringBuilder();
            JSONObject columns = JSON.parseObject(joinColumns);
            for(Object key : columns.keySet())
            {
                joinDBColumns.append(key + ",");
            }
            String sql = "SELECT " + joinDBColumns
                    + pagexListSett.getModelConfig().get("pkey") + " AS pkey FROM "
                    + pagexListSett.getModelConfig().get("table") + " WHERE "
                    +  pagexListSett.getModelConfig().get("pkey") + " IN (@{pkeys})";
            modelSqlMap.put(namespace,sql);
            modelJoinFiledMap.put(namespace,JSON.parseObject(joinColumns, new TypeReference<HashMap<String,String>>() {}));
        }
        Map<String,String> namespaceJoinColumnInfo = new HashMap<>();
        pagexListSett.getListSett().forEach(column->{
            //如果是join
            if(ConverterUtils.toBoolean(column.get("isJoin")))
            {
                if(!namespaceJoinColumnInfo.containsKey(ConverterUtils.toString(column.get("name"))))
                {
                    namespaceJoinColumnInfo.put(ConverterUtils.toString(column.get("name")),
                            ConverterUtils.toString(column.get("namespace")));
                }
            }
        });
        if(namespaceJoinColumnInfo.isEmpty())
        {
            return;
        }
        namespaceJoinColumnMap.put(namespace,namespaceJoinColumnInfo);

    }
}