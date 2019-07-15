package com.fhs.pagex.trans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.trans.ITransTypeService;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransService;
import com.fhs.pagex.dto.PagexListSettDTO;
import com.fhs.pagex.service.PageXDBService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.system.trans.TransMessageListener;
import com.mybatis.jpa.common.ColumnNameUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * pagex的翻译服务实现
 */
@Component
public class PageXTransServiceImpl implements ITransTypeService, InitializingBean {

    private static final Logger LOGGER = Logger.getLogger(PageXTransServiceImpl.class);

    /**
     * key namespace + _ + pkey value 是对应的缓存字段
     */
    private Map<String,Map<String,String>> pageXCacheMap = new HashMap<>();

    /**
     *  pagex中和db 打交道的service 用于缓存数据查询
     */
    @Autowired
    private PageXDBService pageXDBService;

    @Override
    public void transOne(SuperBean<?> obj, List<Field> toTransList) {
        Trans tempTrans = null;
        for (Field tempField : toTransList)
        {
            tempField.setAccessible(true);
            tempTrans = tempField.getAnnotation(Trans.class);
            String pkey = StringUtil.toString(ReflectUtils.getValue(obj, tempField.getName()));
            String namespace = tempTrans.key();
            String alias = null;
            // 如果是port#in alias == in namespace = port
            if(namespace.contains("#"))
            {
                alias = namespace.substring(namespace.indexOf("#")+1);
                namespace = namespace.substring(0,namespace.indexOf("#"));
            }
            Map<String,String> transCache = pageXCacheMap.get(namespace + "_" + pkey);
            if(transCache==null)
            {
                LOGGER.error(namespace + "_" + pkey + "未翻译成功");
                return;
            }
            if(alias != null)
            {
                Map<String,String> tempMap =new HashMap<>();
                Set<String> keys = transCache.keySet();
                for(String key : keys)
                {
                    tempMap.put(alias + "_" + key,transCache.get(key));
                }
                transCache = tempMap;
            }
            obj.getTransMap().putAll(transCache);
        }
    }

    @Override
    public void transMore(List<? extends SuperBean<?>> objList, List<Field> toTransList) {
        objList.forEach(obj->{
            this.transOne(obj,toTransList);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //注册自己为一个服务
        TransService.registerTransType("pagex", this);
        TransMessageListener.regTransRefresher("pagex",this::refreshPageXCache);
    }

    /**
     * 刷新缓存
     * @param messageMap  消息
     */
    public void refreshPageXCache(Map<String,Object> messageMap)
    {
        //这里必须能拿到namespace 拿不到,就当作全部刷新
        String namespace = ConverterUtils.toString(messageMap.get("namespace"),null);
        if(namespace == null)
        {
           Set<String> namespaceSet = PagexDataService.SIGNEL.getAllJsNamespace();
           namespaceSet.forEach(temp->{
                   refreshOneNamespace(temp);
           });
        }else
        {
            refreshOneNamespace(namespace);
        }
    }

    /**
     * 刷新一个namespace下的所有的缓存
     * @param namespace  namespace
     */
    public void refreshOneNamespace(String namespace)
    {
        LOGGER.info("开始刷新pagex缓存:" + namespace);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("start",Constant.PAGE_ALL);
        PagexListSettDTO pagexListSettDTO = PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace);
        String pkeyField = ConverterUtils.toString(pagexListSettDTO.getModelConfig().get("pkeyCamel"));
        JSONObject joinColumns = JSON.parseObject(ConverterUtils.toString(pagexListSettDTO.getModelConfig().get("joinColumns")));
        //没有配置则代表不需要提供翻译给其他的代码
        if(joinColumns==null)
        {
            return;
        }
        JSONObject row = null;
        String rows =  pageXDBService.findListPage(paramMap,namespace);
        JSONArray rowsJson = JSON.parseArray(rows);
        String fieldCamel = null;
        String pkeyVal  = null;
        String fielVal = null;
        Map<String,String> tempCacheTransMap = null;
        for(int i = 0;i<rowsJson.size();i++)
        {
            row  = rowsJson.getJSONObject(i);
            pkeyVal = row.getString(pkeyField);
            Set<String> columns = joinColumns.keySet();
            tempCacheTransMap = new HashMap<>();
            for(String column : columns)
            {
                fieldCamel = ColumnNameUtil.underlineToCamel(ConverterUtils.toString(column));
                fielVal = ConverterUtils.toString(row.get(fieldCamel));
                tempCacheTransMap.put(joinColumns.getString(column),fielVal);
            }
            pageXCacheMap.put(namespace+"_"+pkeyVal,tempCacheTransMap);
        }
        LOGGER.info("刷新pagex缓存完成:" + namespace);
    }
}
