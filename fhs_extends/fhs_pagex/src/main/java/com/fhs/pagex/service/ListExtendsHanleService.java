package com.fhs.pagex.service;

import com.alibaba.fastjson.JSONArray;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.logger.Logger;
import com.fhs.pagex.dto.PagexListSettDTO;
import com.fhs.pagex.fi.ListDataExtendsHandle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * pagex 列表数据扩展处理
 * 比如分钟转几天几小时显示
 * 比如根据结束日期获取结束日期距离现在的时长
 */
@Service
public class ListExtendsHanleService implements InitializingBean {

    private static final Logger LOG = Logger.getLogger(ListExtendsHanleService.class);

    /**
     * 不需要处理数据的namespace
     */
    private Set<String> notNeedHandleNamespace = new HashSet<>();

    private Map<String, ListDataExtendsHandle> handleMap = new HashMap<>();

    /**
     * 需要处理数据的namespace以及需要处理 哪些列
     */
    private Map<String, List<Map<String, Object>>> notNeedHandleNamespaceSettMap = new HashMap<>();


    /**
     * 处理数据
     *
     * @param namespace namespace
     * @param rows      数据集合多行
     */
    public void processingData(String namespace, JSONArray rows) {
        if (notNeedHandleNamespace.contains(namespace)) {
            return;
        }
        //下面这些代码是处理哪些列配置了handle，然后缓存到map中
        List<Map<String, Object>> needHandleFieldList = null;
        if (notNeedHandleNamespaceSettMap.containsKey(namespace)) {
            needHandleFieldList = notNeedHandleNamespaceSettMap.get(namespace);
        } else {
            PagexListSettDTO listSettDTO = PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace);
            needHandleFieldList = listSettDTO.getListSett().stream().filter(new Predicate<Map<String, Object>>() {
                @Override
                public boolean test(Map<String, Object> stringObjectMap) {
                    return stringObjectMap.containsKey("handle");
                }
            }).collect(Collectors.toList());
            notNeedHandleNamespaceSettMap.put(namespace,needHandleFieldList);
        }
        if (needHandleFieldList == null || needHandleFieldList.isEmpty()) {
            notNeedHandleNamespace.add(namespace);
            return;
        }
        needHandleFieldList.forEach(field -> {
            String handleType = ConverterUtils.toString(field.get("handle"));
            if (handleMap.containsKey(handleType)) {
                handleMap.get(handleType).handleData(field,rows);
            } else {
                LOG.error("namespace:" + namespace + "的列：" + field + "缺少对应的数据处理handle");
            }
        });
    }


    /**
     * 注册pagex数据处理器
     *
     * @param type   handle类型
     * @param handle 方法
     */
    public void registerHandle(String type, ListDataExtendsHandle handle) {
        handleMap.put(type, handle);
    }

    /**
     * 当namespace的js 刷新的时候
     *
     * @param namespace namespace
     * @param jsContent 新的js的内容
     */
    public void onJsRefresh(String namespace, String jsContent) {
        notNeedHandleNamespace.remove(namespace);
        notNeedHandleNamespaceSettMap.remove(namespace);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PagexDataService.SIGNEL.registerJsRefreshListener(this::onJsRefresh);
    }


}
